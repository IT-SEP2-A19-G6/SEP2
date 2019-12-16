package shared;

import java.io.Serializable;
import java.util.ArrayList;

public class Ticket implements Serializable {


    private int id;
    private int clientId;
    private int branchId;
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

    public void setAssignee(String username) {
        assignee = username;
    }

}
