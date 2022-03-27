package org.pcsoft.framework.jnap.api;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NativeCall {
    String library();
    String method();

    LowLevelType returnValue() default LowLevelType.AUTO;
}
