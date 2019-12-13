package shared;

import java.time.LocalDate;
import java.util.Date;

public class TicketReply {
    private String message;
    private LocalDate timeStamp;
    private String username;
    private int ticketId;
    private int replyId;

    public TicketReply(String message, LocalDate timeStamp, String username, int ticketId, int replyId) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.username = username;
        this.ticketId = ticketId;
        this.replyId = replyId;
    }

    public TicketReply(String message, String username, int ticketId) {
        this.message = message;
        this.username = username;
        this.ticketId = ticketId;
    }

    public TicketReply(int id, int ticketId, Date timestamp, String username, String message) {
    }

    public String getMessage() {
        return message;
    }

    public java.sql.Date getTimeStamp() {
        return timeStamp;
    }

    public String getUsername() {
        return username;
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getReplyId() { return replyId;
    }
}
