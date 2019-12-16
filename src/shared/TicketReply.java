package shared;

import java.io.Serializable;

public class TicketReply implements Serializable {
    private final String message;
    private String timeStamp;
    private String username;
    private final int ticketId;
    private int clientId;


    public TicketReply(String message, String timeStamp, String username, int ticketId) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.username = username;
        this.ticketId = ticketId;
    }

    public TicketReply(String message, int ticketId, int clientId) {
        this.message = message;
        this.clientId = clientId;
        this.ticketId = ticketId;

    }

    public String getMessage() {
        return message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getUsername() {
        return username;
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getClientId() {
        return clientId;
    }
}
