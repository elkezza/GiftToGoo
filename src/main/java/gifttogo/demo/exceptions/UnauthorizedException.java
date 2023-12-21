package gifttogo.demo.exceptions;

public class UnauthorizedException extends GenericException{
    public  UnauthorizedException(String errorName, String errorMessage) {
        super(errorName, errorMessage);
    }
}
