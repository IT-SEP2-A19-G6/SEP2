package shared;

import java.io.Serializable;
import java.util.ArrayList;

public class TicketListExchange implements Serializable {
    String username;
    String message;
    Request.TYPE action;
    ArrayList<Ticket> ticketList;

    public TicketListExchange(Request.TYPE action, String username) {
        this.username = username;
        this.action = action;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public Request.TYPE getAction() {
        return action;
    }

    public ArrayList<Ticket> getTickets() {
        return ticketList;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAction(Request.TYPE action) {
        this.action = action;
    }

    public void setTicketList(ArrayList<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
}