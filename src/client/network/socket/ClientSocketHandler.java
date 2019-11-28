package client.network.socket;

import shared.Request;
import shared.Response;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketHandler implements IClientSocketHandler {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private Socket socket;
    private ObjectInputStream inputFromServer;
    private ObjectOutputStream outputToServer;
    private boolean activeConnection;

    public ClientSocketHandler(Socket socket) {
        activeConnection = true;
        this.socket = socket;
        try {
            outputToServer = new ObjectOutputStream(socket.getOutputStream());
            inputFromServer = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendToServer(Request request){
            try {
                outputToServer.writeObject(request);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void closeConnection() {
        Request closeReq = new Request(Request.TYPE.CLOSE_CONNECTION, "");
        sendToServer(closeReq);
    }


    @Override
    public void run() {
        try {
            while (activeConnection) {
                Request requestFromServer = (Request) inputFromServer.readObject();

                if (requestFromServer.type.equals(Request.TYPE.CLOSE_CONNECTION)) {
                    activeConnection = false;
                    try {
                        Thread.sleep(500);
                        inputFromServer.close();
                        outputToServer.close();
                        socket.close();
                    } catch (IOException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
                } else if (requestFromServer.type.equals(Request.TYPE.LOGIN_RESPONSE)){
                        Response loginResponse = (Response) requestFromServer.object;
                        support.firePropertyChange(Request.TYPE.LOGIN_RESPONSE.name(), "", loginResponse);
                    }
                }

                //TODO unwrap request and call model methods() here... (ex. loginModel)

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

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
