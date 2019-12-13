package shared;

import client.util.ClientProperties;

import java.io.Serializable;
import java.time.LocalDate;

public class TicketReply implements Serializable {
    private String message;
    private LocalDate timeStamp;
    private String username;
    private int ticketId;
    private int clientid;

    public TicketReply(String message, LocalDate timeStamp, String username, int ticketId) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.username = username;
        this.ticketId = ticketId;
    }

    public TicketReply(int ticketId, String message, int clientid) {
        this.ticketId = ticketId;
        this.message = message;
        this.clientid = clientid;
    }

    public String getMessage() {
        return message;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public String getUsername() {
        return username;
    }

    public int getTicketId() {
        return ticketId;
    }
}
