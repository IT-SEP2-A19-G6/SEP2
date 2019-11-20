package client.network;

import client.model.ModelFactory;
import client.model.login.LoginModel;
import shared.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketHandler implements Runnable {

    private ObjectInputStream inputFromServer;
    private ObjectOutputStream outputToServer;
    private LoginModel loginModel;

    public ClientSocketHandler(Socket socket, ModelFactory modelFactory) {
        this.loginModel = modelFactory.getLoginModel();
        addListeners(modelFactory);
        try {
            outputToServer = new ObjectOutputStream(socket.getOutputStream());
            inputFromServer = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addListeners(ModelFactory modelFactory) {
        //TODO add listeners here like this -> modelFactory.getLoginModel().addListener(Request.TYPE.LOGIN_REQ.name(), this::sendToServer);
    }



    @Override
    public void run() {
        try {
            while (true) {
                Request request = (Request) inputFromServer.readObject();

                //TODO unwrap request and call model methods() here... (ex. loginModel)

            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ClientSocketHandler EXCEPTION: " + e.getMessage());
        }

    }
}
