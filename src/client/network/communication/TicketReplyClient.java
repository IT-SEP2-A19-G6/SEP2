package client.network.communication;

import client.network.socket.IClientSocketHandler;
import shared.Request;
import shared.TicketReply;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class TicketReplyClient implements ITicketReplyClient {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final IClientSocketHandler clientSocketHandler;

    public TicketReplyClient(IClientSocketHandler clientSocketHandler) {
        this.clientSocketHandler = clientSocketHandler;
        addListeners();
    }

    private void addListeners() {
        clientSocketHandler.addPropertyChangeListener(Request.TYPE.TICKET_REPLY_RESPONSE.name(), this::handleReplies);
    }

    private void handleReplies(PropertyChangeEvent propertyChangeEvent) {
        Request request = (Request) propertyChangeEvent.getNewValue();
        //noinspection unchecked
        ArrayList<TicketReply> replies = (ArrayList<TicketReply>) request.object;
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

}
