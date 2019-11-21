package client.model.login;

import shared.IPropertyChangeSubject;
import shared.Request;
import shared.clients.Client;
import shared.clients.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.UnknownServiceException;
import java.util.LinkedList;
import java.util.List;

public class LoginModelHandler implements LoginModel, IPropertyChangeSubject {

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void validateLogin(String username, String password) {
        Client client = new User(username, password);
        support.firePropertyChange(Request.TYPE.LOGIN_REQ.name(), "", client);
    }

    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name, listener);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        support.removePropertyChangeListener(name, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
