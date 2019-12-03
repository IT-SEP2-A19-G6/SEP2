package client.network.user;

import client.network.socket.IClientSocketHandler;
import shared.Request;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UserClientHandler implements IUserClient {
    private IClientSocketHandler clientSocketHandler;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public UserClientHandler(IClientSocketHandler clientSocketHandler) {
        this.clientSocketHandler = clientSocketHandler;
        addListeners();
    }

    private void addListeners() {
        clientSocketHandler.addPropertyChangeListener(Request.TYPE.TICKET_LIST_RESPONSE.name(), this::handleResponse);
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        support.firePropertyChange(propertyChangeEvent.getPropertyName(), "", propertyChangeEvent.getNewValue());
    }


    @Override
    public void requestTicketList(String username) {
        Request request = new Request(Request.TYPE.TICKET_LIST_REQ, username);
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
