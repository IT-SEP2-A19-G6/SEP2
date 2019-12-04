package client.model.createticket;

import client.network.ticket.ICreateTicketClient;
import shared.Request;
import shared.Response;
import shared.Ticket;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DummyCreateTicketClient implements ICreateTicketClient {

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void submitTicket(Ticket ticket) {
        // fireing dummy 'ok'
        Response r = new Response("", "OK");
        support.firePropertyChange(Request.TYPE.TICKET_RECEIVE.name(), "", r);
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
