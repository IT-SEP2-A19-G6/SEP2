package testdummies;

import client.network.socket.IClientSocketHandler;
import shared.Branch;
import shared.Request;
import shared.Response;
import shared.Ticket;
import shared.clients.Client;
import shared.clients.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

@SuppressWarnings("CanBeFinal")
public class TestSocketHandler implements IClientSocketHandler {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private User testUser;

    public TestSocketHandler(){
        testUser = new User("correctuser", "correctPassword");
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
        } else if (Request.TYPE.TICKET_CREATE.equals(request.type)) {

            Request req = new Request(Request.TYPE.TICKET_RECEIVE, new Response("OK"));
            saveTicket = (Ticket) request.object;
            support.firePropertyChange(Request.TYPE.TICKET_RECEIVE.name(), "", req);

        }

    }
    Ticket saveTicket;
    public Ticket sentTicket(){
        return saveTicket;
    }

    public void fireBranchResponse(ArrayList<Branch> branches) {
        support.firePropertyChange(Request.TYPE.BRANCH_RESPONSE.name(), "", new Request(Request.TYPE.BRANCH_RESPONSE, branches));
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

}
