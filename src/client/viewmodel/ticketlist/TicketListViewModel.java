package client.viewmodel.ticketlist;

import client.model.ticketlist.ITicketListModel;
import client.util.ClientProperties;
import client.viewmodel.client.ClientViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.Request;
import shared.Ticket;
import shared.TicketListExchange;

import java.beans.PropertyChangeEvent;

public class TicketListViewModel {

    ITicketListModel ticketListModel;
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
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {

        TicketListExchange fromServer = (TicketListExchange) propertyChangeEvent.getNewValue();
        tickets.clear();
        tickets.addAll(fromServer.getTickets());

        System.out.println("Got tickets:");
        System.out.println(tickets);
    }

    public ObservableList<Ticket> getTickets() {
        return tickets;
    }





}
