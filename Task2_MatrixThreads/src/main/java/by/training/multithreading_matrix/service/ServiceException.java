package by.training.multithreading_matrix.service;

public class ServiceException extends Exception {
    /**Constructor without parameters.*/
    public ServiceException() {
        super();
    }
    /**Constr. with one parameter.
     * @param message message.
     */
    public ServiceException(final String message) {
        super(message);
    }
    /**Constr. with two parameters.
     * @param message message.
     * @param cause   cause.
     */
    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }
    /**Constr. with one parameter.
     * @param cause cause.
     */
    public ServiceException(final Throwable cause) {
        super(cause);
    }
}
