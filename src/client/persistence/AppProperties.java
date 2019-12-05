package client.persistence;

import shared.clients.Client;

public class AppProperties {

    // Lazy instantiation
    private static AppProperties instance;
    private Client client;


    private AppProperties(){}

    public static AppProperties getInstance(){
        if(instance == null){
            instance = new AppProperties();
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
