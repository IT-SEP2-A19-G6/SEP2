package shared;

import shared.clients.User;

import java.io.Serializable;

public class Response implements Serializable {
    private String toUser;
    private String message;

    public Response(String toUser, String message) {
        this.toUser = toUser;
        this.message = message;
    }


    public String getToUser() {
        return toUser;
    }

    public String getMessage() {
        return message;
    }
}
