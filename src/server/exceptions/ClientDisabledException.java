package Shared.exceptions;

public class ClientDisabledException extends Exception {

    public ClientDisabledException(){}


    public ClientDisabledException(String message){
        super(message);
    }

}
