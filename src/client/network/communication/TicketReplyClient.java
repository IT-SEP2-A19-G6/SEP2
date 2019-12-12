package client.network.communication;

import client.network.socket.IClientSocketHandler;
import shared.IPropertyChangeSubject;
import shared.Request;
import shared.TicketReply;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class TicketReplyClient implements ITicketReplyClient {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private IClientSocketHandler clientSocketHandler;

    public TicketReplyClient(IClientSocketHandler clientSocketHandler) {
        this.clientSocketHandler = clientSocketHandler;
        addListeners();
    }

    private void addListeners() {
        clientSocketHandler.addPropertyChangeListener(Request.TYPE.TICKET_REPLY_RESPONSE.name(), this::handleReplies);
    }

    private void handleReplies(PropertyChangeEvent propertyChangeEvent) {
        ArrayList<TicketReply> replies = (ArrayList<TicketReply>) propertyChangeEvent.getNewValue();
        support.firePropertyChange(Request.TYPE.TICKET_REPLY_RESPONSE.name(), "", replies);
    }

    @Override
    public void getReplies(int ticketId) {
        clientSocketHandler.sendToServer(new Request(Request.TYPE.TICKET_REPLIES_REQ, ticketId));
    }

    @Override
    public void addReply(TicketReply reply) {
        clientSocketHandler.sendToServer(new Request(Request.TYPE.ADD_TICKET_REPLY, reply));
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
