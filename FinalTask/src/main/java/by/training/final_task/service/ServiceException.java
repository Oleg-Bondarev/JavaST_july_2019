package by.training.final_task.service;

public class ServiceException extends Exception {
    public ServiceException() { }

    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ServiceException(final String message) {
        super(message);
    }

    public ServiceException(final Throwable cause) {
        super(cause);
    }
}
