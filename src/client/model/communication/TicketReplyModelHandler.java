package client.model.communication;

import client.network.communication.ITicketReplyClient;
import shared.IPropertyChangeSubject;
import shared.Request;
import shared.TicketReply;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TicketReplyModelHandler implements ITicketReplyModel {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ITicketReplyClient ticketReplyClient;


    public TicketReplyModelHandler(ITicketReplyClient ticketReplyClient) {
        this.ticketReplyClient = ticketReplyClient;
        addListeners();
    }

    private void addListeners() {
        ticketReplyClient.addPropertyChangeListener(Request.TYPE.TICKET_REPLY_RESPONSE.name(), this::handleReplies);
    }

    private void handleReplies(PropertyChangeEvent propertyChangeEvent) {
        support.firePropertyChange(propertyChangeEvent.getPropertyName(), propertyChangeEvent.getOldValue(), propertyChangeEvent.getNewValue());
    }

    @Override
    public void getReplies(int ticketId) {
        ticketReplyClient.getReplies(ticketId);
    }

    @Override
    public void addReply(TicketReply reply) {
        ticketReplyClient.addReply(reply);
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
