package org.pcsoft.framework.jnap.internal.utils;

import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.MemoryLayout;
import jdk.incubator.foreign.ValueLayout;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.pcsoft.framework.jnap.api.LowLevelType;
import org.pcsoft.framework.jnap.api.NativeType;
import org.pcsoft.framework.jnap.exceptions.JNAPAnnotationException;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ParameterTypeUtils {

    public static MemoryLayout from(Parameter parameter, Method method) {
        final var annotation = parameter.getAnnotation(NativeType.class);
        if (annotation == null)
            throw new JNAPAnnotationException(parameter, method, NativeType.class);

        return from(annotation.value(), parameter.getType());
    }

    @NonNull
    public static MemoryLayout from(LowLevelType type, Class<?> clazz) {
        if (type == LowLevelType.AUTO) {
            if (clazz == void.class)
                throw new IllegalArgumentException("VOID is not allowed here");
            if (clazz == int.class || clazz == Integer.class)
                return ValueLayout.JAVA_INT;
            if (clazz == short.class || clazz == Short.class)
                return ValueLayout.JAVA_SHORT;
            if (clazz == long.class || clazz == Long.class)
                return ValueLayout.JAVA_LONG;
            if (clazz == boolean.class || clazz == Boolean.class)
                return ValueLayout.JAVA_BOOLEAN;
            if (clazz == byte.class || clazz == Byte.class)
                return ValueLayout.JAVA_BYTE;
            if (clazz == double.class || clazz == Double.class)
                return ValueLayout.JAVA_DOUBLE;
            if (clazz == float.class || clazz == Float.class)
                return ValueLayout.JAVA_FLOAT;

            return ValueLayout.ADDRESS;
        }

        return from(type);
    }

    @NonNull
    private static MemoryLayout from(LowLevelType type) {
        return switch (type) {
            case CHAR -> ValueLayout.JAVA_CHAR;
            case INT -> ValueLayout.JAVA_INT;
            case ADDRESS -> ValueLayout.ADDRESS;
            case VOID -> throw new IllegalArgumentException("VOID is not allowed here");
            case BOOLEAN -> ValueLayout.JAVA_BOOLEAN;
            case BYTE -> ValueLayout.JAVA_BYTE;
            case SHORT -> ValueLayout.JAVA_SHORT;
            case LONG -> ValueLayout.JAVA_LONG;
            case FLOAT -> ValueLayout.JAVA_FLOAT;
            case DOUBLE -> ValueLayout.JAVA_DOUBLE;
            default -> throw new IllegalArgumentException("Unknown type " + type.name());
        };
    }

}
