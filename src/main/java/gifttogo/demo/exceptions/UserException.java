package gifttogo.demo.exceptions;

public class UserException extends GenericException {
    public UserException(String errorName, String errorMessage) {
        super(errorName, errorMessage);
    }
}
