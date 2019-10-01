package by.training.final_task.service.parser;

public class InvalidFormDataException  extends Exception {

    public InvalidFormDataException(){}

    public InvalidFormDataException(String message) {
        super(message);
    }

    public InvalidFormDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFormDataException(Throwable cause) {
        super(cause);
    }
}
