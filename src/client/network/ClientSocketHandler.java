package client.network;

import client.model.ModelFactory;
import client.model.login.ILoginModel;
import shared.IPropertyChangeSubject;
import shared.Request;
import shared.Response;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketHandler implements Runnable {

    private ObjectInputStream inputFromServer;
    private ObjectOutputStream outputToServer;
    private ILoginModel loginModel;

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
        modelFactory.getLoginModel().addPropertyChangeListener(Request.TYPE.LOGIN_REQ.name(), this::handleLoginReq);
    }

    private void handleLoginReq(PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getNewValue() != null){
            Request request = new Request(Request.TYPE.LOGIN_REQ, propertyChangeEvent.getNewValue());
            sendToServer(request);
        }
    }

    private void sendToServer(Request request){
            try {
                outputToServer.writeObject(request);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    @Override
    public void run() {
        try {
            while (true) {
                Request requestFromServer = (Request) inputFromServer.readObject();

                if (requestFromServer.type.equals(Request.TYPE.LOGIN_RESPONSE)){
                    Response loginResponse = (Response) requestFromServer.object;
                    loginModel.loginResult(loginResponse);
                }

                //TODO unwrap request and call model methods() here... (ex. loginModel)

            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ClientSocketHandler EXCEPTION: " + e.getMessage());
        }

    }

    //TODO add graceful connection shutdown
}
