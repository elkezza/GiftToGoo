package gifttogo.demo.exceptions;

public class ValidationException extends GenericException {
    public ValidationException(String errorName, String errorMessage) {
        super(errorName, errorMessage);
    }
}
