package client.model.ticketlist;

import client.network.ticketlist.ITicketListClient;
import shared.Request;
import shared.Ticket;
import shared.TicketListExchange;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TicketListModelHandler implements ITicketListModel {
    private final ITicketListClient ticketListClient;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public TicketListModelHandler(ITicketListClient userClient) {
        this.ticketListClient = userClient;
        ticketListClient.addPropertyChangeListener(Request.TYPE.TICKET_LIST_RESPONSE.name(), this::HandleResponse);
        ticketListClient.addPropertyChangeListener(Request.TYPE.BRANCH_MEMBERS_BY_BRANCHNAME_REPLY.name(), this::HandleResponseBranchMembers);
    }

    private void HandleResponseBranchMembers(PropertyChangeEvent propertyChangeEvent) {
        support.firePropertyChange(propertyChangeEvent.getPropertyName(), null , propertyChangeEvent.getNewValue());
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
    public void setTicketStatus(Ticket ticket) {
        ticketListClient.setTicketStatus(ticket);
    }

    @Override
    public void requestBranchMembersByBranchName(String branchName) {
        ticketListClient.requestBranchMembersByBranchName(branchName);
    }

    @Override
    public void setAssignee(Ticket ticket) {
        ticketListClient.setAssignee(ticket);
    }

    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        if (name == null){
            support.addPropertyChangeListener(listener);
        } else {
            support.addPropertyChangeListener(name, listener);
        }
    }

}
