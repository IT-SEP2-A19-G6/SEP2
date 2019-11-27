package client.model.login;

import shared.IPropertyChangeSubject;
import shared.Request;
import shared.clients.Client;
import shared.clients.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginModelHandler implements ILoginModel, IPropertyChangeSubject {

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void validateLogin(String username, String password) {
        Client client = new User(username, password);
        support.firePropertyChange(Request.TYPE.LOGIN_REQ.name(), "", client);
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
