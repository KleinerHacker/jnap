package org.pcsoft.framework.jnap.api;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface NativeType {
    LowLevelType value() default LowLevelType.AUTO;
}
