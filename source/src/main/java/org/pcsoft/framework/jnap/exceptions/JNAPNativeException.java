package org.pcsoft.framework.jnap.exceptions;

public class JNAPNativeException extends JNAPExecutionException{
    public JNAPNativeException() {
    }

    public JNAPNativeException(String message) {
        super(message);
    }

    public JNAPNativeException(String message, Throwable cause) {
        super(message, cause);
    }

    public JNAPNativeException(Throwable cause) {
        super(cause);
    }
}
