package client.util;

import shared.clients.Client;
import shared.clients.ClientType;
import shared.clients.User;

public class ClientProperties {

    // Lazy instantiation
    private static ClientProperties instance;
    private Client client;


    private ClientProperties(){}

    public static ClientProperties getInstance(){
        if(instance == null){
            instance = new ClientProperties();
        }
        return instance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }



}
