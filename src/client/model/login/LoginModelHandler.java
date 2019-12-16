package client.model.login;

import client.network.login.ILoginClient;
import client.util.ClientProperties;
import shared.Request;
import shared.Response;
import shared.clients.Client;
import shared.clients.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginModelHandler implements ILoginModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final ILoginClient loginClient;

    public LoginModelHandler(ILoginClient loginClient){
        this.loginClient = loginClient;
        addListeners();
    }

    private void addListeners() {
        loginClient.addPropertyChangeListener(Request.TYPE.LOGIN_RESPONSE.name(), this::handleResponse);
    }

    @Override
    public void validateLogin(String username, String password) {
        Client client = new User(username, password);
        loginClient.validateLogin(client);
    }


    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        Response loginResponse = (Response) propertyChangeEvent.getNewValue();
        if (loginResponse.getReceiver() != null) {
            ClientProperties.getInstance().setClient(loginResponse.getReceiver());
            support.firePropertyChange(Request.TYPE.SET_STATE.name(), "", ClientProperties.getInstance().getClient().getType());
        }
        support.firePropertyChange(propertyChangeEvent.getPropertyName(), propertyChangeEvent.getOldValue(), propertyChangeEvent.getNewValue());

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
