package client.network;

import client.network.login.ILoginClient;
import client.network.login.LoginClientHandler;
import client.network.socket.ClientSocketHandler;
import client.network.socket.IClientSocketHandler;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;


public class ClientFactory {
    private IClientSocketHandler clientSocketHandler;
    private ILoginClient loginClient;

    public ClientFactory() {
        System.out.println("Client is starting...");

        try {
            Socket socket = new Socket("localhost", 2920);
            clientSocketHandler = new ClientSocketHandler(socket);
            Thread t = new Thread(clientSocketHandler);
             t.start();
        } catch (ConnectException e) {
            System.out.println("Unable to connect to server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        clientSocketHandler.closeConnection();
    }

    public ILoginClient getLoginClient(){
        if (loginClient == null){
            loginClient = new LoginClientHandler(clientSocketHandler);
        }
        return loginClient;
    }


}
