package shared;

import java.sql.Date;
import java.time.LocalDate;

public class TicketReply {
    private String message;
    private LocalDate timeStamp;
    private String username;
    private int ticketId;
    private int replyId;

    public TicketReply(int replyId,int ticketId, LocalDate timeStamp, String username ,String message ) {
        this.replyId = replyId;
        this.ticketId = ticketId;
        this.timeStamp = timeStamp;
        this.username = username;
        this.message = message;
    }

    public TicketReply(String message, String username, int ticketId) {
        this.message = message;
        this.username = username;
        this.ticketId = ticketId;
    }


    public String getMessage() {
        return message;
    }

    public Date getTimeStamp() {
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
