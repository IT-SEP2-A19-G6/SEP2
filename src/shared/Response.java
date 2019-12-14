package shared;

import shared.clients.Client;


import java.io.Serializable;

public class Response implements Serializable {
    private Client receiver;
    private String message;

    public Response(Client receiver, String message) {
        this.receiver = receiver;
        this.message = message;
    }

    public Response(String message){
        this.message = message;
    }


    public Client getReceiver() {
        return receiver;
    }

    public String getMessage() {
        return message;
    }
}
