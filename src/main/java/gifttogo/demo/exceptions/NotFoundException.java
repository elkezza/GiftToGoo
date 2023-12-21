package gifttogo.demo.exceptions;

public class NotFoundException extends GenericException{
    public NotFoundException(String errorName, String errorMessage) {
        super(errorName, errorMessage);
    }
}
