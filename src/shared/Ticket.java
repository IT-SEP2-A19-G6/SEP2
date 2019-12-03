package shared;

import java.util.ArrayList;

public class Ticket {
    private String user;
    private ArrayList<String> branches;
    private ArrayList<String> branchMembers;
    private String subject;
    private String description;
    private String location;
    private TicketStatus ticketStatus;


    public Ticket(String subject, String description, String location) {
        this.subject = subject;
        this.description = description;
        this.location = location;
        ticketStatus = TicketStatus.OPEN;
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "user='" + user + '\'' +
                ", branches=" + branches +
                ", branchMembers=" + branchMembers +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", ticketStatus=" + ticketStatus +
                '}';
    }

    public enum TicketStatus{
        OPEN,
        IN_PROGRESS,
        CLOSED
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }



    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }
}
