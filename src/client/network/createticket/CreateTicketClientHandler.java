package client.network.createticket;


import client.network.socket.IClientSocketHandler;
import shared.Request;
import shared.Response;
import shared.Ticket;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateTicketClientHandler implements ICreateTicketClient {


    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private IClientSocketHandler clientSocketHandler;

    public CreateTicketClientHandler(IClientSocketHandler clientSocketHandler){
        this.clientSocketHandler =  clientSocketHandler;
        addListeners();
    }

    private void addListeners() {
        clientSocketHandler.addPropertyChangeListener(Request.TYPE.TICKET_RECEIVE.name(), this::handleResponse);
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        Request serverReq = (Request) propertyChangeEvent.getNewValue();
        if (serverReq.type.name().equals(Request.TYPE.TICKET_RECEIVE.name())) {
            Response createTicket = (Response) serverReq.object;
            support.firePropertyChange(serverReq.type.name(), "", createTicket);
        }
    }
    @Override
    public Ticket createTicket(Ticket ticket) {
        Request createTicketReq = new Request(Request.TYPE.TICKET_CREATE, ticket);
        clientSocketHandler.sendToServer(createTicketReq);
        return ticket;
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
