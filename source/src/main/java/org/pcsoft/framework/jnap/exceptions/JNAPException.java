package org.pcsoft.framework.jnap.exceptions;

public abstract class JNAPException extends RuntimeException {
    public JNAPException() {
    }

    public JNAPException(String message) {
        super(message);
    }

    public JNAPException(String message, Throwable cause) {
        super(message, cause);
    }

    public JNAPException(Throwable cause) {
        super(cause);
    }
}
