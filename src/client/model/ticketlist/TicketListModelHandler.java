package client.model.ticketlist;

import client.network.ticketlist.ITicketListClient;
import shared.Request;
import shared.TicketListExchange;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TicketListModelHandler implements ITicketListModel {
    private ITicketListClient ticketListClient;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public TicketListModelHandler(ITicketListClient userClient) {
        this.ticketListClient = userClient;
        ticketListClient.addPropertyChangeListener(Request.TYPE.TICKETLIST_RESPONSE.name(), this::HandleResponse);
    }

    private void HandleResponse(PropertyChangeEvent propertyChangeEvent) {
        TicketListExchange exchangeFromServer = (TicketListExchange)propertyChangeEvent.getNewValue();
        support.firePropertyChange(exchangeFromServer.getAction().name(), "", exchangeFromServer);
    }

    @Override
    public void requestTicketList(TicketListExchange exchange) {
        ticketListClient.requestTicketList(exchange);
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
