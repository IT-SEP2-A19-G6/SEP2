package client.model.signup;

import client.network.signup.ISignUpClient;
import shared.Request;
import shared.clients.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignUpModelHandler implements ISignUpModel {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ISignUpClient signUpClient;

    public SignUpModelHandler(ISignUpClient signUpClient) {
        this.signUpClient = signUpClient;
        addListners();
    }

    private void addListners() {
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
