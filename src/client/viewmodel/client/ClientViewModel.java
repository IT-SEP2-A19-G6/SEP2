package client.viewmodel.client;

import client.model.ticketlist.ITicketListModel;
import client.util.ClientProperties;
import client.viewmodel.client.states.BranchMemberState;
import client.viewmodel.client.states.UserState;
import client.viewmodel.client.states.IClientState;
import client.viewmodel.client.uielements.TicketList;
import client.viewmodel.client.uielements.TicketListItem;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import shared.Request;
import shared.Ticket;
import shared.TicketListExchange;
import shared.clients.ClientType;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class ClientViewModel {
    private ITicketListModel ticketListModel;
    private ObservableList<Node> rightArea;
    private ObservableList<Node> menuItems;
    private IClientState currentState;
    private StringProperty callNewTicket; //TODO delete after incorporation of ticket

    public ClientViewModel(ITicketListModel ticketListModel) {
        this.ticketListModel = ticketListModel;
        rightArea = FXCollections.observableArrayList();
        menuItems = FXCollections.observableArrayList();
        setInitState();
        callNewTicket = new SimpleStringProperty(); //TODO delete after incorporation of ticket
    }

    private void setInitState(){
        if (ClientProperties.getInstance().getClient().getType().equals(ClientType.USER)){
            setState(new UserState());
        } else if (ClientProperties.getInstance().getClient().getType().equals(ClientType.BRANCH_MEMBER)) {
            setState(new BranchMemberState());
        }
    }

    public void setState(IClientState newState){
        if (currentState != null){
            currentState.exit();
        }
        currentState = newState;
        currentState.entry(this);
    }

    public ObservableValue<? extends String> showTicketProperty() {
        return callNewTicket;
    }

    public ObservableList<Node> setRightArea(){
        return rightArea;
    }

    public ObservableList<Node> menuNodes(){
        return menuItems;
    }


    public void buildTicketList(PropertyChangeEvent propertyChangeEvent) {
        TicketListExchange fromServer = (TicketListExchange) propertyChangeEvent.getNewValue();
        TicketList listToBuild = new TicketList();

        ArrayList<Ticket> ticketsFromServer = fromServer.getTickets();
        clearRightArea();
        for (Ticket ticket : ticketsFromServer){
            TicketListItem listItem = new TicketListItem(this, ticket.getId(), ticket.getCreatedDate(), ticket.getUsername(), ticket.getSubject(), ticket.getDescription(), ticket.getCategory(), ticket.getLocation(), ticket.getTicketStatus(), ticket.getBranch(), ticket.getAssignee());
            listToBuild.addTicketToList(listItem.getTicketListItem());
        }
        buildRightArea(listToBuild.getTicketList());
    }

   private void showNoTicketsMessage(PropertyChangeEvent propertyChangeEvent) {
       TicketListExchange messageFromServer = (TicketListExchange) propertyChangeEvent.getNewValue();
       clearRightArea();
       buildRightArea(new TicketList().getEmptyList(messageFromServer.getMessage()));
    }

    public void requestOwnTicketList(){
        ticketListModel.addPropertyChangeListener(Request.TYPE.OWN_TICKET_LIST_RESPONSE.name(), this::buildTicketList);
        ticketListModel.addPropertyChangeListener(Request.TYPE.NO_TICKETS_FOUND_RESPONSE.name(), this::showNoTicketsMessage);
        ticketListModel.requestTicketList(new TicketListExchange(Request.TYPE.OWN_TICKET_LIST_REQ, ClientProperties.getInstance().getClient().getUsername()));
    }


    public void requestAssignedTicketList(){
        ticketListModel.addPropertyChangeListener(Request.TYPE.ASSIGNED_TICKET_LIST_RESPONSE.name(), this::buildTicketList);
        ticketListModel.addPropertyChangeListener(Request.TYPE.NO_TICKETS_ASSIGNED_RESPONSE.name(), this::showNoTicketsMessage);
        ticketListModel.requestTicketList(new TicketListExchange(Request.TYPE.ASSIGNED_TICKET_LIST_REQ, ClientProperties.getInstance().getClient().getUsername()));
    }

    public void requestBranchTicketList() {
        ticketListModel.addPropertyChangeListener(Request.TYPE.BRANCH_TICKET_LIST_RESPONSE.name(), this::buildTicketList);
        ticketListModel.addPropertyChangeListener(Request.TYPE.NO_TICKETS_IN_BRANCH_RESPONSE.name(), this::showNoTicketsMessage);
        ticketListModel.requestTicketList(new TicketListExchange(Request.TYPE.BRANCH_TICKET_LIST_REQ, ClientProperties.getInstance().getClient().getUsername()));
    }

    public void getTicket(String id) {
        //TODO get exixting ticket by ID here
        System.out.println(id);
    }

    public void getTicketList() {
        if (ClientProperties.getInstance().getClient().getType().equals(ClientType.USER)){
            requestOwnTicketList();
        } else if (ClientProperties.getInstance().getClient().getType().equals(ClientType.BRANCH_MEMBER)){
            requestAssignedTicketList();
        }
    }

    public void getBranchList() {
        requestBranchTicketList();
    }

    public void createNewTicket() {
        //TODO change to ticketView here
        callNewTicket.setValue("new ticket");
    }

    public void resetTicketCall(){
        callNewTicket.setValue("");
    } //TODO delete after ticket is incorporated into view

    public void clearMenu(){
        Platform.runLater(() ->{
            menuItems.clear();
        });
    }

    public void clearRightArea(){
        Platform.runLater(() ->{
            rightArea.clear();
        });
    }

    public void buildMenu(Node node){
        Platform.runLater(() ->{
            menuItems.add(node);
        });
    }

    public void buildRightArea(Node node){
        Platform.runLater(() ->{
            rightArea.add(node);
        });
    }


}
