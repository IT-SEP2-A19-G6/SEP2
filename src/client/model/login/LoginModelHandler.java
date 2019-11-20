package client.model.login;

import shared.IPropertyChangeSubject;
import shared.clients.Client;
import shared.clients.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.UnknownServiceException;
import java.util.LinkedList;
import java.util.List;

public class LoginModelHandler implements LoginModel, IPropertyChangeSubject {

    private List<Client> clients = new LinkedList<>();
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void validateLogin(String username, String password) {
        String result = checkLoginCredentials(username, password);
        support.firePropertyChange("LoginResult", "", result);
    }

    private String checkLoginCredentials(String username, String password) {
        Client client = findUser(username);
        if(client == null) {
            return "User not found";
        }
        if(!client.getUsername().equals(username)){
            return "Incorrect username";
        }
        if(!client.getPassword().equals(password)) {
            return "Incorrect password";
        }
        return "OK";
    }

    private Client findUser(String username) {
        Client client = null;
        for (Client c : clients) {
            if(c.getUsername().equals(username)) {
                client = c;
                break;
            }
        }
        return client;
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
