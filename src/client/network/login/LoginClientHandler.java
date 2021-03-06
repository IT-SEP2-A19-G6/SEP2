package client.network.login;

import client.network.socket.IClientSocketHandler;
import shared.Request;
import shared.Response;
import shared.clients.Client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginClientHandler implements ILoginClient {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final IClientSocketHandler clientSocketHandler;

    public LoginClientHandler(IClientSocketHandler clientSocketHandler){
        this.clientSocketHandler = clientSocketHandler;
        addListeners();
    }

    private void addListeners() {
        clientSocketHandler.addPropertyChangeListener(Request.TYPE.LOGIN_RESPONSE.name(), this::handleResponse);
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        Request serverReq = (Request) propertyChangeEvent.getNewValue();
        Response loginResponse = (Response) serverReq.object;
        support.firePropertyChange(serverReq.type.name(), "", loginResponse);
    }

    @Override
    public void validateLogin(Client client) {
        Request loginReq = new Request(Request.TYPE.LOGIN_REQ, client);
        clientSocketHandler.sendToServer(loginReq);
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
