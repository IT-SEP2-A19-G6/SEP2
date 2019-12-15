package client.network.admin;

import client.network.socket.IClientSocketHandler;
import shared.Branch;
import shared.BranchListExchange;
import shared.Request;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class AdminClientHandler implements IAdminClient {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    IClientSocketHandler clientSocketHandler;

    public AdminClientHandler(IClientSocketHandler clientSocketHandler) {
        this.clientSocketHandler = clientSocketHandler;
        addListeners();
    }

    private void addListeners() {
        clientSocketHandler.addPropertyChangeListener(Request.TYPE.BRANCH_RESPONSE.name(), this::handleBranches);
        clientSocketHandler.addPropertyChangeListener(Request.TYPE.ADD_BRANCH_RESPONSE.name(), this::handleResponse);


    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        support.firePropertyChange(propertyChangeEvent.getPropertyName(), propertyChangeEvent.getOldValue(), propertyChangeEvent.getNewValue());
    }

    private void handleBranches(PropertyChangeEvent propertyChangeEvent) {
        ArrayList<Branch> branches = (ArrayList<Branch>) propertyChangeEvent.getNewValue();
        support.firePropertyChange(propertyChangeEvent.getPropertyName(), "", branches);
    }

    @Override
    public void addBranch(Branch branch) {
        Request request = new Request(Request.TYPE.ADD_BRANCH_REQ, branch);
        clientSocketHandler.sendToServer(request);
    }

    @Override
    public void getBranches() {
        clientSocketHandler.sendToServer(new Request(Request.TYPE.BRANCH_REQ, ""));
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
