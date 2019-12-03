package client.model.user;

import client.network.user.IUserClient;
import shared.Request;
import shared.Response;
import shared.Ticket;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class UserModelHandler implements IUserModel {
    private IUserClient userClient;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ArrayList<Ticket> userTickets;

    public UserModelHandler(IUserClient userClient) {
        this.userClient = userClient;
        addListeners();
    }

    private void addListeners() {
        userClient.addPropertyChangeListener(Request.TYPE.TICKET_LIST_RESPONSE.name(), this::handleResponse);
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        Request response = (Request) propertyChangeEvent.getNewValue();
        ArrayList<Ticket> ticketsFromServer = (ArrayList<Ticket>) response.object;
        if (ticketsFromServer != null && ticketsFromServer.size() > 0){
            support.firePropertyChange(propertyChangeEvent.getPropertyName(), "", ticketsFromServer);
            userTickets.addAll(ticketsFromServer);
        } else {
            Response noTicketResponse = new Response("", "No tickets yet - try add one...");
            support.firePropertyChange(Request.TYPE.NO_TICKETS_FOUND_RESPONSE.name(), "", noTicketResponse);
        }
    }

    @Override
    public void requestTicketList(String username) {
        if (userTickets == null){ //TODO might cause a bug if we do not add tickets below
            userTickets = new ArrayList<>();
            userClient.requestTicketList(username);
        } else {
            support.firePropertyChange(Request.TYPE.TICKET_LIST_RESPONSE.name(), "", userTickets);
        }
    }

    //TODO delete or use
//    @Override
//    public void addTicket() {
//        Ticket ticket = new Ticket("Subject" + i, "A new super important description" + i, "location" + i);
//        userTickets.add(0, ticket);
//        i++;
//        support.firePropertyChange(Request.TYPE.TICKET_LIST_RESPONSE.name(), "", userTickets);
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
