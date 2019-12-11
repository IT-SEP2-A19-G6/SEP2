package client.util;

import shared.clients.Client;

public class ClientProperties {

    // Lazy instantiation
    private static ClientProperties instance;
    private Client client;


    private ClientProperties(){

    }

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
