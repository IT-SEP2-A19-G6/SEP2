package server.network;

import client.model.login.LoginModel;
import server.model.ServerModelFactory;
import server.model.login.LoginServerModel;
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
        loginServerModel.addPropertyChangeListener(Request.TYPE.LOGIN_ACCEPT.name(), this::handlePropertyChange);
        //TODO add listeners here
    }

    private void handlePropertyChange(PropertyChangeEvent propertyChangeEvent) {
        //TODO handle propertyChanges
        if (propertyChangeEvent.getPropertyName().equals(Request.TYPE.LOGIN_ACCEPT.name())){

        }
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
                Request request = (Request) inputFromClient.readObject();

                if (request.type.equals(Request.TYPE.LOGIN_REQ)){
                    User user = (User) request.object;
                    loginServerModel.validateLogin(user);
                }

                //TODO create methods to take care of the newly received objects.

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
