package shared.exceptions;

public class IncorrectCredentialsException extends Exception {

    public IncorrectCredentialsException(){}

    public IncorrectCredentialsException(String message){
        super(message);
    }

}
