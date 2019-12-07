package shared;

import java.io.Serializable;
import java.time.LocalDate;

public class Ticket implements Serializable {


    private int id;
    private int userId;
    private String createdDate;
    private String username;
    private String subject;
    private String description;
    private String category;
    private String location;
    private String ticketStatus;
    private String branch;
    private String assignee;

    public Ticket(int id, String createdDate, String username, String subject, String description, String category, String location, String ticketStatus, String branch, String assignee) {
        this.id = id;
        this.createdDate = createdDate;
        this.username = username;
        this.subject = subject;
        this.description = description;
        this.category = category;
        this.location = location;
        this.ticketStatus = ticketStatus;
        this.branch = branch;
        this.assignee = assignee;
    }

    public Ticket(int id, int clientId, String subject, String description, String category, String location, String ticketStatus) {
        this.id = id;
        this.userId = clientId;
        this.subject = subject;
        this.description = description;
        this.category = category;
        this.location = location;
        this.ticketStatus = ticketStatus;
    }
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

    public int getId() {
        return id;
    }

    public String getBranch() {
        return branch;
    }

    public String getAssignee() {
        return assignee;
    }

    public String getCreatedDate() {
        return createdDate;
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
