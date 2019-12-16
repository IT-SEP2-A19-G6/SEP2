package client.model.communication;

import client.network.communication.ITicketReplyClient;
import client.util.ClientProperties;
import shared.Request;
import shared.TicketReply;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TicketReplyModelHandler implements ITicketReplyModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final ITicketReplyClient ticketReplyClient;


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

    @SuppressWarnings("SpellCheckingInspection")
    @Override
    public void addReply(int ticketid, String message) {
        TicketReply reply = new TicketReply(message, ticketid, ClientProperties.getInstance().getClient().getClientId());
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

}
