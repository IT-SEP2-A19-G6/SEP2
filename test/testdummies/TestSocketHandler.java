package testdummies;

import client.network.socket.IClientSocketHandler;
import shared.Request;
import shared.Response;
import shared.clients.Client;
import shared.clients.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TestSocketHandler implements IClientSocketHandler {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private User testUser;

    public TestSocketHandler(){
        testUser = new User("correctUser", "correctPassword");
    }


    @Override
    public void sendToServer(Request request) {
        if (request.type.equals(Request.TYPE.LOGIN_REQ)){
            Client userToCheck = (User) request.object;
            String loginMessage;
            if (userToCheck.getUsername().equals(testUser.getUsername())){
                if (userToCheck.getPassword().equals(testUser.getPassword())){
                    loginMessage = "User login accepted";
                } else {
                    loginMessage = "Incorrect credentials";
                }
            } else {
                loginMessage = "Incorrect credentials";
            }
            Response loginResponse = new Response(userToCheck, loginMessage);
            Request serverReq = new Request(Request.TYPE.LOGIN_RESPONSE, loginResponse);
            support.firePropertyChange(Request.TYPE.LOGIN_RESPONSE.name(), "", serverReq);
        }

    }

    @Override
    public void closeConnection() {

    }

    @Override
    public void run() {

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
