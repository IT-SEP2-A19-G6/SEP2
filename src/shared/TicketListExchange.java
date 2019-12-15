package shared;

import shared.clients.Client;

import java.io.Serializable;
import java.util.ArrayList;

public class TicketListExchange implements Serializable {
    Client client;
    String message;
    Request.TYPE action;
    ArrayList<Ticket> ticketList;

    public TicketListExchange(Request.TYPE action, Client client) {
        this.client = client;
        this.action = action;
    }

    public Client getClient() {
        return client;
    }

    public String getMessage() {
        return message;
    }

    public Request.TYPE getAction() {
        return action;
    }

    public ArrayList<Ticket> getTickets() {
        return getList();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAction(Request.TYPE action) {
        this.action = action;
    }

    public void setTicketList(ArrayList<Ticket> ticketList) {
        getList().clear();
        getList().addAll(ticketList);
    }

    private ArrayList<Ticket> getList(){
        if (ticketList == null){
            ticketList = new ArrayList<>();
        }
        return ticketList;
    }
}
