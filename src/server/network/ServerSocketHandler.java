package server.network;

import server.model.ServerModelFactory;
import server.model.login.LoginServerModel;
import shared.Response;
import shared.Request;
import shared.clients.User;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketHandler implements Runnable {

private ObjectOutputStream outputToClient;
private ObjectInputStream inputFromClient;
private LoginServerModel loginServerModel;


    public ServerSocketHandler(Socket socket, ServerModelFactory serverModelFactory) {
        this.loginServerModel = serverModelFactory.getLoginServerModel();
        addListeners();
        try {
            outputToClient = new ObjectOutputStream(socket.getOutputStream());
            inputFromClient = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("ServerSocketHandler EXCEPTION: " + e.getMessage());
        }
    }

    private void addListeners() {
        //TODO add listeners here
    }

    private void handlePropertyChange(PropertyChangeEvent propertyChangeEvent) {
        //TODO handle propertyChanges
    }

    private void sendToClient(Request request){
        try {
            outputToClient.writeObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            while (true) {

                //TODO remember to change the method depending on what kind of object needs to be casted.
                Request requestFromClient = (Request) inputFromClient.readObject();

                if (requestFromClient.type.equals(Request.TYPE.LOGIN_REQ)){
                    User user = (User) requestFromClient.object;
                    Response message = loginServerModel.validateLogin(user);
                    Request requestToClient = new Request(Request.TYPE.LOGIN_RESPONSE, message);
                    sendToClient(requestToClient);
                }

                //TODO create methods to take care of the newly received objects.

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //TODO add graceful connection shutdown
}
