package shared;

import java.time.LocalDate;

public class TicketReply {
    private String message;
    private LocalDate timeStamp;
    private String username;
    private int ticketId;

    public TicketReply(String message, LocalDate timeStamp, String username, int ticketId) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.username = username;
        this.ticketId = ticketId;
    }

    public TicketReply(String message, String username, int ticketId) {
        this.message = message;
        this.username = username;
        this.ticketId = ticketId;
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
