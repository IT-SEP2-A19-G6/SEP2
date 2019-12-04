package shared;

import shared.clients.User;

import java.io.Serializable;

public class Response implements Serializable {
    private User toUser;
    private String message;

    public Response(User toUser, String message) {
        this.toUser = toUser;
        this.message = message;
    }

    public User getToUser() {
        return toUser;
    }

    public String getMessage() {
        return message;
    }
}
