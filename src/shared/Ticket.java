package shared;

import java.io.Serializable;

public class Ticket implements Serializable {



    private int userId;
    private String username;
    private String subject;
    private String description;
    private String category;
    private String location;
    private String ticketStatus;



    public Ticket(int clientId, String subject, String description, String category, String location, String ticketStatus) {
        this.userId = clientId;
        this.subject = subject;
        this.description = description;
        this.category = category;
        this.location = location;
        this.ticketStatus = ticketStatus;
    }

    public Ticket(String subject, String description, String location) {
        this.subject = subject;
        this.description = description;
        this.location = location;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "user=" + userId +
                ", username='" + username + '\'' +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", location='" + location + '\'' +
                ", ticketStatus='" + ticketStatus + '\'' +
                '}';
    }
}
