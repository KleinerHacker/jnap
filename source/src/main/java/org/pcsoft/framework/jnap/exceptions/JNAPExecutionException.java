package org.pcsoft.framework.jnap.exceptions;

public class JNAPExecutionException extends JNAPException{
    public JNAPExecutionException() {
    }

    public JNAPExecutionException(String message) {
        super(message);
    }

    public JNAPExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public JNAPExecutionException(Throwable cause) {
        super(cause);
    }
}
