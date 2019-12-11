package client.viewmodel.ticketlist;

import client.model.ticketlist.ITicketListModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.Request;
import shared.Ticket;
import shared.TicketListExchange;

import java.beans.PropertyChangeEvent;


public class TicketListViewModel {

    private ITicketListModel ticketListModel;
    private ObservableList<Ticket> tickets;
    private StringProperty responseMessage;

    public TicketListViewModel(ITicketListModel ticketListModel) {
        this.ticketListModel = ticketListModel;
        tickets = FXCollections.observableArrayList();
        responseMessage = new SimpleStringProperty();
        addListeners();
    }

    public void requestTickets(TicketListExchange ticketListExchange) {
        ticketListModel.requestTicketList(ticketListExchange);
    }

    private void addListeners() {
        ticketListModel.addPropertyChangeListener(Request.TYPE.TICKET_LIST_RESPONSE.name(), this::handleResponse);
        ticketListModel.addPropertyChangeListener(Request.TYPE.NO_TICKETS_FOUND_RESPONSE.name(), this::handleEmptyResponse);
    }

    private void handleEmptyResponse(PropertyChangeEvent propertyChangeEvent) {
        TicketListExchange fromServer = (TicketListExchange) propertyChangeEvent.getNewValue();
        responseMessage.setValue(fromServer.getMessage());
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        TicketListExchange fromServer = (TicketListExchange) propertyChangeEvent.getNewValue();
        tickets.setAll(fromServer.getTickets());
    }

    public ObservableList<Ticket> getTickets() {
        return tickets;
    }

    public StringProperty responseMessageProperty() { return responseMessage; }

    public void resetResponseMessage(){ responseMessage.setValue(""); }

}
