package by.training.task15_resourcePool;

public class ResourceException extends Exception {
    /**Exc without params.*/
    public ResourceException() {
        super();
    }
    /**Exc with two params.
     * @param cause -cause of the exc.
     * @param message of the exc.*/
    public ResourceException(final String message, final Throwable cause) {
        super(message, cause);
    }
    /**Exc with one param.
     * @param message of the exc.*/
    public ResourceException(final String message) {
        super(message);
    }
    /**Exc with one param.
     * @param cause of the exc.*/
    public ResourceException(final Throwable cause) {
        super(cause);
    }
}
