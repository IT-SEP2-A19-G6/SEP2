package client.network.communication;

import client.network.socket.IClientSocketHandler;
import shared.IPropertyChangeSubject;
import shared.TicketReply;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TicketReplyClient implements ITicketReplyClient, IPropertyChangeSubject {
    private IClientSocketHandler clientSocketHandler;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public TicketReplyClient(IClientSocketHandler clientSocketHandler) {
        this.clientSocketHandler = clientSocketHandler;
    }

    @Override
    public void getReplies(int ticketId) {
        
    }

    @Override
    public void addReply(TicketReply reply) {

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
