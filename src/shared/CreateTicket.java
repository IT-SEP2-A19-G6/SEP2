package shared;

import shared.clients.User;

import java.io.Serializable;

public class CreateTicket implements Serializable {

    private String username;
    private String subject;
    private String description;
    private String category;
    private String location;
    private String ticketStatus;



    public CreateTicket(String username, String subject, String description, String category, String location, String ticketStatus) {
        this.username = username;
        this.subject = subject;
        this.description = description;
        this.category = category;
        this.location = location;
        this.ticketStatus = ticketStatus;
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
        return "CreateTicket{" +
                "username='" + username + '\'' +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", location='" + location + '\'' +
                ", ticketStatus='" + ticketStatus + '\'' +
                '}';
    }
}
