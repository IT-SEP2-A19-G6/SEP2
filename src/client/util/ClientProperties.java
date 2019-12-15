package client.util;

import shared.clients.BranchMember;
import shared.clients.Client;

import java.util.ArrayList;

public class ClientProperties {

    // Lazy instantiation
    private static ClientProperties instance;
    private Client client;
    private ArrayList<BranchMember> branchMembers;



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

    public ArrayList<BranchMember> getBranchMembers() {
        return branchMembers;
    }

    public void setBranchMembers(ArrayList<BranchMember> branchMembers) {
        this.branchMembers = branchMembers;
    }



}
