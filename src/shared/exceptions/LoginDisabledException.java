package shared.exceptions;

public class LoginDisabledException extends Exception {

    public LoginDisabledException(){}

    public LoginDisabledException(String message){
        super(message);
    }

}
