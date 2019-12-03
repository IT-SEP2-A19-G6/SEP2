package client.model.user;

import client.network.user.IUserClient;
import shared.Request;
import shared.Ticket;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class UserModelHandler implements IUserModel {
    private IUserClient userClient;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ArrayList<Ticket> userTickets; //TODO DELETE
    private int i = 0;

    public UserModelHandler(IUserClient userClient) {
        this.userClient = userClient;
        addListeners();
        userTickets = new ArrayList<>();
//        //TODO DELETE below
//        for (i = 0; i < 5; i++) {
//            Ticket ticket = new Ticket("Subject " + i, "Some super important description" + i, "location " + i);
//            userTickets.add(ticket);
//        }
    }

    private void addListeners() {
        userClient.addPropertyChangeListener(Request.TYPE.TICKET_LIST_RESPONSE.name(), this::handleResponse);
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        ArrayList<Ticket> ticketsFromServer = (ArrayList<Ticket>) propertyChangeEvent.getNewValue();
        if (ticketsFromServer.size() > 0){
            userTickets.addAll(ticketsFromServer);
            support.firePropertyChange(propertyChangeEvent.getPropertyName(), "", propertyChangeEvent.getNewValue());
        }
    }

    @Override
    public void requestTicketList(String username) {
        userClient.requestTicketList(username);
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
