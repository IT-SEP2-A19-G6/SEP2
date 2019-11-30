package client.network.socket;

import shared.Request;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.EOFException;
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
                support.firePropertyChange(requestFromServer.type.name(), "", requestFromServer);
            }
        } catch (EOFException e){ //server closed connection
            activeConnection = false;
            try {
                inputFromServer.close();
                outputToServer.close();
                socket.close();
                System.out.println("Client closed");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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
