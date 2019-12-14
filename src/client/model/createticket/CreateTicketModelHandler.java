package client.model.createticket;

import client.network.createticket.ICreateTicketClient;
import client.util.ClientProperties;
import shared.Branch;
import shared.Request;
import shared.Ticket;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class CreateTicketModelHandler implements ICreateTicketModel{


    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ICreateTicketClient createTicketClient;
    private ArrayList<Branch> branches;


    public CreateTicketModelHandler(ICreateTicketClient createTicketClient) {
        this.createTicketClient = createTicketClient;
        addListeners();
    }

    private void addListeners() {
        createTicketClient.addPropertyChangeListener(Request.TYPE.TICKET_RECEIVE.name(), this::handleTicketResponse);
        createTicketClient.addPropertyChangeListener(Request.TYPE.BRANCH_RESPONSE.name(), this::handleBranchResponse);
    }

    private void handleBranchResponse(PropertyChangeEvent propertyChangeEvent) {
        branches = (ArrayList<Branch>) propertyChangeEvent.getNewValue();
        ArrayList<String> branchNames = new ArrayList<>();
        for (Branch branch : branches){
            branchNames.add(branch.getBranchName());
        }
        support.firePropertyChange(propertyChangeEvent.getPropertyName(), "", branchNames);
    }

    private void handleTicketResponse(PropertyChangeEvent propertyChangeEvent) {
        support.firePropertyChange(propertyChangeEvent.getPropertyName(), propertyChangeEvent.getOldValue(), propertyChangeEvent.getNewValue());
    }

    @Override
    public void submitTicket(String subject, String category, String description, String location) {
        int branchId = 0;
        for (Branch branch : branches){
            if (category.equals(branch.getBranchName())){
                branchId = branch.getId();
            }
        }
        Ticket ticket = new Ticket(ClientProperties.getInstance().getClient().getClientId(), subject, description, location, branchId);
        createTicketClient.submitTicket(ticket);
    }

    @Override
    public void getBranches() {
        createTicketClient.getBranches();
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
