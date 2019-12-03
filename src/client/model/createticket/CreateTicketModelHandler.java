package client.model.createticket;

import client.network.ticket.ICreateTicketClient;
import shared.IPropertyChangeSubject;
import shared.Request;
import shared.Ticket;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateTicketModelHandler implements ICreateTicketModel, IPropertyChangeSubject {


    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ICreateTicketClient createTicketClient;


    public CreateTicketModelHandler(ICreateTicketClient createTicketClient) {
        this.createTicketClient = createTicketClient;
    }



    private void addListeners() {
        createTicketClient.addPropertyChangeListener(Request.TYPE.TICKET.name(), this::handleResponse);
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        support.firePropertyChange(propertyChangeEvent.getPropertyName(), propertyChangeEvent.getOldValue(), propertyChangeEvent.getNewValue());
    }

    @Override
    public void submitTicket(String subject, String description, String location) {
        Ticket ticket = new Ticket(subject, description, location);
        // TODO: Add network handler like the commented out below, Change enum if needed
        //createTicketClient.submitTicket(ticket);
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
