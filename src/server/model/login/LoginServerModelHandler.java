package server.model.login;

/* SOMEone edited?
import server.persistence.DataFactory;

public class LoginServerModelHandler implements LoginServerModel {
    DataFactory dataFactory;

    public LoginServerModelHandler(DataFactory dataFactory) {
        this.dataFactory = dataFactory;
    }
*/

import client.model.login.LoginModel;
import shared.clients.Client;

import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.List;

public class LoginServerModelHandler implements LoginServerModel{
    private LoginModel loginModel;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private List<Client> clients= new LinkedList<>();

    @Override
    public void validateData(String username, String password) {
    loginModel.validateLogin(username, password);
    String result = checkLoginCredentials(username, password);
    support.firePropertyChange("LoginResult","",result);
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

}
