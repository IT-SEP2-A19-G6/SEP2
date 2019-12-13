package shared;

import client.util.ClientProperties;

import java.io.Serializable;
import java.time.LocalDate;

public class TicketReply implements Serializable {
    private String message;
    private String timeStamp;
    private String username;
    private int ticketId;
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
