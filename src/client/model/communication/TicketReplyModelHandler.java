package client.model.communication;

import client.network.communication.ITicketReplyClient;
import shared.IPropertyChangeSubject;
import shared.TicketReply;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TicketReplyModelHandler implements ITicketReplyModel, IPropertyChangeSubject {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ITicketReplyClient ticketReplyClient;


    public TicketReplyModelHandler(ITicketReplyClient ticketReplyClient) {
        this.ticketReplyClient = ticketReplyClient;
    }

    @Override
    public void getReplies(int ticketId) {

    }

    @Override
    public void addReply(TicketReply reply) {

    }

    @Override
    public void submitReply(String messageArea) {

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
