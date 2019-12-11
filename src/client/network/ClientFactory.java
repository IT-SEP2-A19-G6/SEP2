package client.network;

import client.network.login.ILoginClient;
import client.network.login.LoginClientHandler;
import client.network.signup.ISignUpClient;
import client.network.signup.SignUpClientHandler;
import client.network.socket.ClientSocketHandler;
import client.network.socket.IClientSocketHandler;
import client.network.createticket.CreateTicketClientHandler;
import client.network.createticket.ICreateTicketClient;
import client.network.ticketlist.ITicketListClient;
import client.network.ticketlist.TicketListClientHandler;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;


public class ClientFactory {
    private IClientSocketHandler clientSocketHandler;
    private ILoginClient loginClient;
    private ICreateTicketClient createTicketClient;
    private ITicketListClient ticketListClient;
    private ISignUpClient signUpClient;

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

    public ICreateTicketClient getCreateTicketClient() {
        if(createTicketClient == null) {
            createTicketClient = new CreateTicketClientHandler(clientSocketHandler);
        }
        return  createTicketClient;
    }

    public ITicketListClient getTicketListClient(){
        if (ticketListClient == null){
            ticketListClient = new TicketListClientHandler(clientSocketHandler);
        }
        return ticketListClient;
    }

    public ISignUpClient getSignUpClient() {
        if (signUpClient == null) {
            signUpClient = new SignUpClientHandler(clientSocketHandler);
        }
        return signUpClient;
    }

}
