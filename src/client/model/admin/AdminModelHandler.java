package client.model.admin;

import client.network.admin.IAdminClient;
import shared.Branch;
import shared.Request;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class AdminModelHandler implements IAdminModel {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private IAdminClient adminClient;

    public AdminModelHandler(IAdminClient adminClient) {
        this.adminClient = adminClient;
        addListeners();
        getBranches();
    }

    private void addListeners() {
        adminClient.addPropertyChangeListener(Request.TYPE.ADD_BRANCH_RESPONSE.name(), this::handleResponse);
        adminClient.addPropertyChangeListener(Request.TYPE.BRANCH_RESPONSE.name(), this::handleResponse);
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        support.firePropertyChange(propertyChangeEvent.getPropertyName(), propertyChangeEvent.getOldValue(), propertyChangeEvent.getNewValue());
    }

    private void getBranches() {
        adminClient.getBranches();
    }

    @Override
    public void addBranch(String branchName) {
        Branch branch = new Branch(branchName);
        adminClient.addBranch(branch);
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
