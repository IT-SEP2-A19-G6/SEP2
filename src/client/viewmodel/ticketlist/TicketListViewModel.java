package client.viewmodel.ticketlist;

import client.model.ticketlist.ITicketListModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.Request;
import shared.Ticket;
import shared.TicketListExchange;
import shared.clients.BranchMember;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;


public class TicketListViewModel {

    private ITicketListModel ticketListModel;
    private ObservableList<Ticket> tickets;
    private StringProperty responseMessage;
    private ObservableList<BranchMember> branchMembers;

    public TicketListViewModel(ITicketListModel ticketListModel) {
        this.ticketListModel = ticketListModel;
        branchMembers = FXCollections.observableArrayList();
        tickets = FXCollections.observableArrayList();
        responseMessage = new SimpleStringProperty();

        addListeners();
    }

    public void requestTickets(TicketListExchange ticketListExchange) {
        ticketListModel.requestTicketList(ticketListExchange);
    }

    public void requestBranchMembersByBranchName(String branchName) {
        ticketListModel.requestBranchMembersByBranchName(branchName);
    }

    public void setTicketStatus(Ticket ticket) {
        ticketListModel.setTicketStatus(ticket);
    }

    private void addListeners() {
        ticketListModel.addPropertyChangeListener(Request.TYPE.TICKET_LIST_RESPONSE.name(), this::handleResponse);
        ticketListModel.addPropertyChangeListener(Request.TYPE.NO_TICKETS_FOUND_RESPONSE.name(), this::handleEmptyResponse);
        ticketListModel.addPropertyChangeListener(Request.TYPE.BRANCH_MEMBERS_BY_BRANCHNAME_REPLY.name(), this::handleResponseBranchMembers);
    }

    private void handleResponseBranchMembers(PropertyChangeEvent propertyChangeEvent) {
        branchMembers.setAll((ArrayList<BranchMember>) propertyChangeEvent.getNewValue());
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

    public ObservableList<BranchMember> getBranchMembers() {
        return branchMembers;
    }

    public void setAssignee(String assignee, int ticketId) {
        tickets.stream().filter(e -> e.getId() == ticketId).findFirst().orElse(null).setAssignee(assignee);
        Ticket ticket = tickets.stream().filter(e -> e.getId() == ticketId).findFirst().orElse(null);
        ticketListModel.setAssignee(ticket);
    }
}
