package client.model.signup;

import client.network.signup.ISignUpClient;
import shared.Request;
import shared.clients.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignUpModelHandler implements ISignUpModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final ISignUpClient signUpClient;

    public SignUpModelHandler(ISignUpClient signUpClient) {
        this.signUpClient = signUpClient;
        addListeners();
    }

    private void addListeners() {
        signUpClient.addPropertyChangeListener(Request.TYPE.SIGNUP_RESPONSE.name(), this::handleResponse);
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        support.firePropertyChange(propertyChangeEvent.getPropertyName(), propertyChangeEvent.getOldValue(), propertyChangeEvent.getNewValue());
    }

    @Override
    public void requestSignUp(String newUsername, String newPassword) {
        signUpClient.requestSignUp(new User(newUsername, newPassword));
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
