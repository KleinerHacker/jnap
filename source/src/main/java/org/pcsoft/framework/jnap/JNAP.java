package org.pcsoft.framework.jnap;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.pcsoft.framework.jnap.internal.JNAPProxy;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JNAP {

    public static <T>T createProxy(Class<T> clazz, ClassLoader classLoader) {
        return JNAPProxy.create(clazz, classLoader);
    }

    public static <T>T createProxy(Class<T> clazz) {
        return createProxy(clazz, ClassLoader.getSystemClassLoader());
    }

}
