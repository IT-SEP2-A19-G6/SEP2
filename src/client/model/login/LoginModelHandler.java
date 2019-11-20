package client.model.login;

import shared.clients.Client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.List;

public class LoginModelHandler implements LoginModel {
    private LoginModel loginModel;
    private List<Client> clients = new LinkedList<>();
    protected PropertyChangeSupport support = new PropertyChangeSupport(this);


    public void validateLogin(String username, String password) {
        String result = checkLoginCredentials(username, password);
        support.firePropertyChange("LoginResult", "", result);
    }

    private String checkLoginCredentials(String username, String password) {
        Client client = findUser(username);
        if(client == null) {
            return "User not found";
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
    public void addListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name, listener);
    }


}
