package org.pcsoft.framework.jnap.exceptions;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class JNAPAnnotationException extends JNAPException{
    public JNAPAnnotationException(Method method, Class<? extends Annotation> annotationClass) {
        super("Missing required annotation " + annotationClass.getSimpleName() + " on method " + method);
    }

    public JNAPAnnotationException(Parameter parameter, Method method, Class<? extends Annotation> annotationClass) {
        super("Missing required annotation " + annotationClass.getSimpleName() + " on parameter " + parameter.getName() + " on method " + method);
    }
}
