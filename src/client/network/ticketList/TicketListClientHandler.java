package client.network.ticketlist;

import client.network.socket.IClientSocketHandler;
import shared.Request;
import shared.Ticket;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class TicketListClientHandler implements ITicketListClient {
    private IClientSocketHandler clientSocketHandler;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public TicketListClientHandler(IClientSocketHandler clientSocketHandler) {
        this.clientSocketHandler = clientSocketHandler;
        addListeners();
    }

    private void addListeners() {
        clientSocketHandler.addPropertyChangeListener(Request.TYPE.OWN_TICKET_LIST_RESPONSE.name(), this::handleResponse);
        clientSocketHandler.addPropertyChangeListener(Request.TYPE.ASSIGNED_TICKET_LIST_RESPONSE.name(), this::handleResponse);
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        Request listFromServer = (Request) propertyChangeEvent.getNewValue();
        ArrayList<Ticket> ticketsFromServer = (ArrayList<Ticket>) listFromServer.object;
        support.firePropertyChange(propertyChangeEvent.getPropertyName(), "", ticketsFromServer);
    }


    @Override
    public void requestOwnTicketList(String username) {
        Request request = new Request(Request.TYPE.OWN_TICKET_LIST_REQ, username);
        clientSocketHandler.sendToServer(request);
    }

    @Override
    public void requestAssignedTicketList(String username) {
        Request request = new Request(Request.TYPE.ASSIGNED_TICKET_LIST_REQ, username);
        clientSocketHandler.sendToServer(request);
    }

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
