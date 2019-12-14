package client.network.ticketlist;

import client.network.socket.IClientSocketHandler;
import shared.Request;
import shared.Ticket;
import shared.TicketListExchange;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TicketListClientHandler implements ITicketListClient {
    private IClientSocketHandler clientSocketHandler;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public TicketListClientHandler(IClientSocketHandler clientSocketHandler) {
        this.clientSocketHandler = clientSocketHandler;
        this.clientSocketHandler.addPropertyChangeListener(Request.TYPE.TICKET_LIST_RESPONSE.name(), this::handleResponse);
    }


    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        Request reqFromServer = (Request) propertyChangeEvent.getNewValue();
        TicketListExchange exchangeFromServer = (TicketListExchange) reqFromServer.object;
        support.firePropertyChange(propertyChangeEvent.getPropertyName(), "", exchangeFromServer);
    }


    @Override
    public void requestTicketList(TicketListExchange exchange) {
        Request request = new Request(Request.TYPE.TICKET_LIST_REQ, exchange);
        clientSocketHandler.sendToServer(request);
    }

    @Override
    public void setTicketStatus(Ticket ticket) {
        Request request = new Request(Request.TYPE.TICKET_SET_STATUS, ticket);
        clientSocketHandler.sendToServer(request);
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
