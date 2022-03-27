package org.pcsoft.framework.jnap.internal;

import jdk.incubator.foreign.FunctionDescriptor;
import jdk.incubator.foreign.MemoryLayout;
import jdk.incubator.foreign.SymbolLookup;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pcsoft.framework.jnap.api.LowLevelType;
import org.pcsoft.framework.jnap.api.NativeCall;
import org.pcsoft.framework.jnap.exceptions.JNAPAnnotationException;
import org.pcsoft.framework.jnap.exceptions.JNAPExecutionException;
import org.pcsoft.framework.jnap.exceptions.JNAPNativeException;
import org.pcsoft.framework.jnap.internal.utils.ParameterTypeUtils;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JNAPProxy {
    @SuppressWarnings("unchecked")
    public static <T>T create(Class<T> clazz, ClassLoader classLoader) {
        log.info("Create proxy for " + clazz.getName());
        return (T) Proxy.newProxyInstance(classLoader, new Class[]{clazz}, JNAPProxy::handle);
    }

    private static Object handle(Object proxy, Method method, Object[] args) {
        log.info("Call method " + method);

        final var nativeCall = method.getAnnotation(NativeCall.class);
        if (nativeCall == null) {
            if (!method.isDefault())
                throw new JNAPAnnotationException(method, NativeCall.class);

            try {
                log.debug("> Call method as default: " + method);
                return MethodHandles.lookup()
                        .in(method.getDeclaringClass())
                        .unreflectSpecial(method, method.getDeclaringClass())
                        .bindTo(proxy)
                        .invoke(args);
            } catch (Throwable e) {
                throw new JNAPExecutionException("Unable to call default method " + method, e);
            }
        }

        log.debug("> Downcall method " + method);
        final var linker = LibraryManager.getLibrary(nativeCall.library());
        final var downcallHandle = linker.downcallHandle(
                SymbolLookup.loaderLookup().lookup(nativeCall.method()).orElseThrow(
                        () -> new JNAPNativeException("Native method " + nativeCall.method() + " not found in library " + nativeCall.library())),
                createFunctionDescriptor(nativeCall, method)
        );

        try {
            return downcallHandle.invoke(args);
        } catch (Throwable e) {
            throw new JNAPNativeException("Exception while run native method " + nativeCall.method() + " in library " + nativeCall.library(), e);
        }
    }

    private static FunctionDescriptor createFunctionDescriptor(NativeCall nativeCall, Method method) {
        final var paramLayouts = Arrays.stream(method.getParameters())
                .map(x -> ParameterTypeUtils.from(x, method))
                .toArray(MemoryLayout[]::new);

        if (nativeCall.returnValue() == LowLevelType.VOID || (nativeCall.returnValue() == LowLevelType.AUTO && method.getReturnType() == void.class))
            return FunctionDescriptor.ofVoid(paramLayouts);

        return FunctionDescriptor.of(
                ParameterTypeUtils.from(nativeCall.returnValue(), method.getReturnType()),
                paramLayouts
        );
    }
}
