package com.vandenbreemen.mobilesecurestorage.message;

/**
 * <h2>Intro</h2>
 * <p>Application error
 * <h2>Other Details</h2>
 *
 * @author kevin
 */
public class ApplicationError extends Exception {

    public ApplicationError(Throwable exception) {
        super(exception.getLocalizedMessage(), exception);
    }

    public ApplicationError(String message) {
        super(message);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ApplicationError){
            return ((ApplicationError) obj).getMessage() == getMessage();
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (getMessage() != null) {
            return getMessage().hashCode();
        }
        return 0;
    }

    public ApplicationError(String message, Throwable cause) {
        super(message, cause);
    }
}
