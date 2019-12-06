package client.network.login;

import client.network.socket.IClientSocketHandler;
import client.util.ClientProperties;
import shared.Request;
import shared.Response;
import shared.clients.Client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginClientHandler implements ILoginClient {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private IClientSocketHandler clientSocketHandler;

    public LoginClientHandler(IClientSocketHandler clientSocketHandler){
        this.clientSocketHandler = clientSocketHandler;
        addListeners();
    }

    private void addListeners() {
        clientSocketHandler.addPropertyChangeListener(Request.TYPE.LOGIN_RESPONSE.name(), this::handleResponse);
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        Request serverReq = (Request) propertyChangeEvent.getNewValue();
        if (serverReq.type.name().equals(Request.TYPE.LOGIN_RESPONSE.name())) {
            Response loginResponse = (Response) serverReq.object;
            if (loginResponse.getReceiver() != null)
                ClientProperties.getInstance().setClient(loginResponse.getReceiver());


            support.firePropertyChange(serverReq.type.name(), "", loginResponse);

        }
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
