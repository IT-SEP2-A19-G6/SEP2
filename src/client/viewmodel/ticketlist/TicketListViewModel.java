package client.viewmodel.ticketlist;

import client.model.ticketlist.ITicketListModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.Request;
import shared.Ticket;
import shared.TicketListExchange;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;


public class TicketListViewModel {

    private ITicketListModel ticketListModel;
    private ObservableList<Ticket> tickets;

    public TicketListViewModel(ITicketListModel ticketListModel) {
        this.ticketListModel = ticketListModel;
        tickets = FXCollections.observableArrayList();
        addListeners();
    }

    public void requestTickets(TicketListExchange ticketListExchange) {
        ticketListModel.requestTicketList(ticketListExchange);
    }

    private void addListeners() {
        ticketListModel.addPropertyChangeListener(Request.TYPE.BRANCH_TICKET_LIST_RESPONSE.name(), this::handleResponse);
        ticketListModel.addPropertyChangeListener(Request.TYPE.ASSIGNED_TICKET_LIST_RESPONSE.name(), this::handleResponse);
        ticketListModel.addPropertyChangeListener(Request.TYPE.OWN_TICKET_LIST_RESPONSE.name(), this::handleResponse);
        //TODO listen for the different no tickets found and add message to view
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        TicketListExchange fromServer = (TicketListExchange) propertyChangeEvent.getNewValue();
        tickets.setAll(fromServer.getTickets());
    }

    public ObservableList<Ticket> getTickets() {
        return tickets;
    }

}
