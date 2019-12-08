package client.model.ticketlist;

import client.network.ticketlist.ITicketListClient;
import shared.Request;
import shared.Ticket;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class TicketListModelHandler implements ITicketListModel {
    private ITicketListClient ticketListClient;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ArrayList<Ticket> ownTickets;
    private ArrayList<Ticket> assignedTickets;

    public TicketListModelHandler(ITicketListClient userClient) {
        this.ticketListClient = userClient;
        addListeners();
    }

    private void addListeners() {
        ticketListClient.addPropertyChangeListener(Request.TYPE.OWN_TICKET_LIST_RESPONSE.name(), this::handleOwnListResponse);
        ticketListClient.addPropertyChangeListener(Request.TYPE.ASSIGNED_TICKET_LIST_RESPONSE.name(), this::handleAssignedListResponse);
    }

    private void handleOwnListResponse(PropertyChangeEvent propertyChangeEvent) {
        ArrayList<Ticket> ticketsFromServer = (ArrayList<Ticket>) propertyChangeEvent.getNewValue();
        if (ticketsFromServer != null && ticketsFromServer.size() > 0){
            support.firePropertyChange(propertyChangeEvent.getPropertyName(), "", ticketsFromServer);
            ownTickets.addAll(ticketsFromServer);
        } else {
            support.firePropertyChange(Request.TYPE.NO_TICKETS_FOUND_RESPONSE.name(), "", "No Tickets yet - try add one...");
        }
    }

    @Override
    public void requestOwnTicketList(String username) {
        if (ownTickets == null){
            ownTickets = new ArrayList<>();
        }
        if (ownTickets.size() == 0){ //TODO might cause a bug if we do not add tickets below
            ticketListClient.requestOwnTicketList(username);
        } else {
            support.firePropertyChange(Request.TYPE.OWN_TICKET_LIST_RESPONSE.name(), "", ownTickets);
        }
    }

    private void handleAssignedListResponse(PropertyChangeEvent propertyChangeEvent) {
        ArrayList<Ticket> ticketsFromServer = (ArrayList<Ticket>) propertyChangeEvent.getNewValue();
        if (ticketsFromServer != null && ticketsFromServer.size() > 0){
            support.firePropertyChange(propertyChangeEvent.getPropertyName(), "", ticketsFromServer);
            assignedTickets.addAll(ticketsFromServer);
        } else {
            support.firePropertyChange(Request.TYPE.NO_TICKETS_ASSIGNED_RESPONSE.name(), "", "Good job! No tickets to handle");
        }
    }

    @Override
    public void requestAssignedTicketList(String username) {
        if (assignedTickets == null){
            assignedTickets = new ArrayList<>();
        }
        if (assignedTickets.size() == 0){ //TODO might cause a bug if we do not add tickets below
            ticketListClient.requestAssignedTicketList(username);
        } else {
            support.firePropertyChange(Request.TYPE.ASSIGNED_TICKET_LIST_RESPONSE.name(), "", assignedTickets);
        }
    }

    //TODO delete or use
//    @Override
//    public void addOwnTicket() {
//        Ticket ticket = new Ticket("Subject" + i, "A new super important description" + i, "location" + i);
//        ownTickets.add(0, ticket);
//        i++;
//        support.firePropertyChange(Request.TYPE.TICKET_LIST_RESPONSE.name(), "", ownTickets);
//    }

    //TODO delete or use
//    @Override
//    public void addAssignedTicket() {
//        Ticket ticket = new Ticket("Subject" + i, "A new super important description" + i, "location" + i);
//        assignedTickets.add(0, ticket);
//        i++;
//        support.firePropertyChange(Request.TYPE.TICKET_LIST_RESPONSE.name(), "", assignedTickets);
//    }

    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        if (name == null){
            support.addPropertyChangeListener(listener);
        } else {
            support.addPropertyChangeListener(name, listener);
        }
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        if (name == null){
            support.removePropertyChangeListener(listener);
        } else {
            support.removePropertyChangeListener(name, listener);
        }
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
