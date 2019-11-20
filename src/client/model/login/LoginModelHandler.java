package client.model.login;

import shared.clients.Client;

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
    public void createUser(String username, String pw, String pwAgain) {
        String result = attemptCreateUser(username, pw, pwAgain);

        if("OK".equals(result)) {
            // adding the new user
            clients.add(new Client(username, pw));
        }

        support.firePropertyChange("CreateClientResult", "", result);
    }

    private String attemptCreateUser(String username, String pw, String pwAgain) {
        if(username == null) {
            return "Username cannot be empty";
        }
        if(findUser(username) != null) {
            return "Username already exists";
        }
        return validatePasswords(pw, pwAgain);
    }

    private String validatePasswords(String pw, String pwAgain) {
        if (pw == null) {
            return "Password cannot be empty";
        }
        if (!pw.equals(pwAgain)) {
            return "Passwords do not match";
        }
        return "OK";
    }

}
