package server.network;

import server.model.ServerModelFactory;
import server.model.login.ILoginServerModel;
import shared.Response;
import shared.Request;
import shared.clients.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ServerSocketHandler implements Runnable {

private ObjectOutputStream outputToClient;
private ObjectInputStream inputFromClient;
private ILoginServerModel loginServerModel;
private Socket socket;
private boolean activeConnection;


    public ServerSocketHandler(Socket socket, ServerModelFactory serverModelFactory) {
        activeConnection = true;
        this.socket = socket;
        this.loginServerModel = serverModelFactory.getLoginServerModel();
        try {
            outputToClient = new ObjectOutputStream(socket.getOutputStream());
            inputFromClient = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("ServerSocketHandler EXCEPTION: " + e.getMessage());
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
            while (activeConnection) {

                //TODO remember to change the method depending on what kind of object needs to be casted.
                Request requestFromClient = (Request) inputFromClient.readObject();
                if (requestFromClient.type.equals(Request.TYPE.CLOSE_CONNECTION)){
                    closeConnection();
                } else if (requestFromClient.type.equals(Request.TYPE.LOGIN_REQ)){
                    User user = (User) requestFromClient.object;
                    Response message = loginServerModel.validateLogin(user);
                    Request requestToClient = new Request(Request.TYPE.LOGIN_RESPONSE, message);
                    sendToClient(requestToClient);
                } else if (requestFromClient.type.equals(Request.TYPE.TICKET_LIST_REQ)){
                    System.out.println("list req");
                }

                //TODO create methods to take care of the newly received objects.

            }
        } catch (SocketException e) {
            closeConnection(); //client closed the connection without calling close
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private void closeConnection(){
        activeConnection = false;
        try {
            outputToClient.close();
            inputFromClient.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
