package client.network.signup;

import client.network.socket.IClientSocketHandler;
import shared.Request;
import shared.clients.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignUpClientHandler implements ISignUpClient {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final IClientSocketHandler clientSocketHandler;

    public SignUpClientHandler(IClientSocketHandler clientSocketHandler) {
        this.clientSocketHandler = clientSocketHandler;
        addListeners();
    }

    private void addListeners() {
        clientSocketHandler.addPropertyChangeListener(Request.TYPE.SIGNUP_RESPONSE.name(), this::handleResponse);
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        Request fromServer = (Request) propertyChangeEvent.getNewValue();
        support.firePropertyChange(propertyChangeEvent.getPropertyName(), propertyChangeEvent.getOldValue(), fromServer.object);
    }

    @Override
    public void requestSignUp(User user) {
        Request signUpReq = new Request(Request.TYPE.SIGNUP_REQ, user);
        clientSocketHandler.sendToServer(signUpReq);
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
