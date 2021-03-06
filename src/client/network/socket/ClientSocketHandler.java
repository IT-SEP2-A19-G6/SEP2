package client.network.socket;

import shared.Request;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketHandler implements IClientSocketHandler{
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final Socket socket;
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
                Thread.sleep(500);
                inputFromServer.close();
                outputToServer.close();
                socket.close();
                System.out.println("Client closed");
            } catch (IOException | InterruptedException ex) {
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

}
