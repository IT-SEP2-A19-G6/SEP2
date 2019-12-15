package shared;

import java.io.Serializable;
import java.util.ArrayList;

public class Ticket implements Serializable {


    private int branchId;
    private int id;
    private int clientId;
    private String createdDate;
    private String username;
    private String subject;
    private String description;
    private String location;
    private String ticketStatus;
    private String branch;
    private String assignee;
    private ArrayList<TicketReply> replies;

    public Ticket(int id, String createdDate, String username, String subject, String description, String location, String ticketStatus, String branch, String assignee) {
        this.id = id;
        this.createdDate = createdDate;
        this.username = username;
        this.subject = subject;
        this.description = description;
        this.location = location;
        this.ticketStatus = ticketStatus;
        this.branch = branch;
        this.assignee = assignee;
    }

    public Ticket(int id, int clientId, String subject, String description, String location, String ticketStatus) {
        this.id = id;
        this.clientId = clientId;
        this.subject = subject;
        this.description = description;
        this.location = location;
        this.ticketStatus = ticketStatus;
    }
    public Ticket(int clientId, String subject, String description, String location, String ticketStatus) {
        this.clientId = clientId;
        this.subject = subject;
        this.description = description;
        this.location = location;
        this.ticketStatus = ticketStatus;
    }

    public Ticket(String subject, String description, String location) {
        this.subject = subject;
        this.description = description;
        this.location = location;
    }

    public Ticket(int clientId, String subject, String description, String location, int branchId) {
        this.clientId = clientId;
        this.subject = subject;
        this.description = description;
        this.location = location;
        this.branchId = branchId;
    }



    public int getClientId() {
        return clientId;
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

    public int getBranchId() {
        return branchId;
    }

    public void addReply(TicketReply reply) {
        if (replies == null){
            this.replies = new ArrayList<>();
        }
        replies.add(reply);
    }

    public ArrayList<TicketReply> getReplies() {
        if (replies != null){
            return replies;
        }
        return null;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "user=" + clientId +
                ", username='" + username + '\'' +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", ticketStatus='" + ticketStatus + '\'' +
                '}';
    }
}
