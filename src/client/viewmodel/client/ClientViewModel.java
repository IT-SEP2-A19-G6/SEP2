package client.viewmodel.client;

import client.model.ticketlist.ITicketListModel;
import client.util.ClientProperties;
import client.viewmodel.client.states.BranchMemberState;
import client.viewmodel.client.states.UserState;
import client.viewmodel.client.states.IClientState;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import shared.Request;
import shared.Ticket;
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
        addListeners();
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

    private void addListeners() {
        ticketListModel.addPropertyChangeListener(Request.TYPE.OWN_TICKET_LIST_RESPONSE.name(), this::buildTicketList);
        ticketListModel.addPropertyChangeListener(Request.TYPE.NO_TICKETS_FOUND_RESPONSE.name(), this::showNoTicketsMessage);
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

    private void buildTicketList(PropertyChangeEvent propertyChangeEvent) {
        ArrayList<Ticket> ticketsFromServer = (ArrayList<Ticket>) propertyChangeEvent.getNewValue();
        currentState.buildTicketList(ticketsFromServer);
    }

    private void showNoTicketsMessage(PropertyChangeEvent propertyChangeEvent) {
        currentState.showNoTickets();
    }

    public void requestOwnTicketList(){
        ticketListModel.requestOwnTicketList(ClientProperties.getInstance().getClient().getUsername());
    }

    public void requestAssignedTicketList(){
        ticketListModel.requestAssignedTicketList(ClientProperties.getInstance().getClient().getUsername());
    }

    public void getTicket(String id) {
        //TODO get exixting ticket by ID here
        System.out.println(id);
    }

    public void getTicketList() {
        //TODO call ticketList
    }

    public void addNewTicket() {
        //TODO change to ticketView here
        callNewTicket.setValue("new ticket");
    }

    public void resetTicketCall(){
        callNewTicket.setValue("");
    } //TODO delete after ticket is incorporated into view

    public void clearMenu(){
        menuItems.clear();
    }

    public void clearRightArea(){
        rightArea.clear();
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
