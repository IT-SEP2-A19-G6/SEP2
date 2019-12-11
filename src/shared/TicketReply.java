package shared;

public class TicketReply {
    private String Message;
    private String TimeStamp;
    private String username;
    private int ticketId;

    public TicketReply(String message, String timeStamp, String username, int ticketId) {
        Message = message;
        TimeStamp = timeStamp;
        this.username = username;
        this.ticketId = ticketId;
    }

    public TicketReply(String message, String username, int ticketId) {
        Message = message;
        this.username = username;
        this.ticketId = ticketId;
    }

    public String getMessage() {
        return Message;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public String getUsername() {
        return username;
    }

    public int getTicketId() {
        return ticketId;
    }
}
