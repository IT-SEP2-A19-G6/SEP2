package client.viewmodel.user;

import client.model.user.IUserModel;
import client.viewmodel.user.uielements.SideMenu;
import client.viewmodel.user.uielements.TicketList;
import client.viewmodel.user.uielements.TicketListItem;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import shared.Request;
import shared.Response;
import shared.Ticket;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class UserViewModel {
    private IUserModel userModel;
    private ObservableList<Node> rightArea;
    private ObservableList<Node> menuItems;
    private String username;
    private TicketList ticketList;
    private StringProperty callNewTicket;

    public UserViewModel(IUserModel userModel) {
        this.userModel = userModel;
        this.ticketList = new TicketList();
        rightArea = FXCollections.observableArrayList();
        menuItems = FXCollections.observableArrayList();
        callNewTicket = new SimpleStringProperty();
        addListeners();
    }

    private void addListeners() {
        userModel.addPropertyChangeListener(Request.TYPE.TICKET_LIST_RESPONSE.name(), this::handleResponse);
        userModel.addPropertyChangeListener(Request.TYPE.NO_TICKETS_FOUND_RESPONSE.name(), this::showNoTickets);
    }

    private void showNoTickets(PropertyChangeEvent propertyChangeEvent) {
        Response serverResponse = (Response) propertyChangeEvent.getNewValue();
        String message = serverResponse.getMessage();
        rightArea.clear();
        Platform.runLater(() ->{
            rightArea.add(ticketList.getEmptyList(message));
        });
    }

    public  void getTicket(String id) {
        //TODO get exixting ticket by ID here
        System.out.println(id);
    }

    public void getTicketList() {
        //TODO call ticketList
    }

    public void addNewTicket() {
        callNewTicket.setValue("new ticket");
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        ArrayList<Ticket> ticketsFromServer = (ArrayList<Ticket>) propertyChangeEvent.getNewValue();
        rightArea.clear();
        for (Ticket ticket : ticketsFromServer){
            TicketListItem listItem = new TicketListItem(this, "id" + ticket.getSubject(), ticket.getDescription(), "SomeStatus", "SomeBranches", "SomeMember", ticket.getSubject(), "SomeTime", "SomeUpdate");
            ticketList.addTicketToList(listItem.getTicketListItem());
        }
        Platform.runLater(() ->{
            rightArea.add(ticketList.getTicketList());
        });
    }

    public ObservableList<Node> setRightArea(){
        return rightArea;
    }

    public ObservableList<Node> menuNodes(){
        return menuItems;
    }

    public void initView(String username) {
        this.username = username;
        requestTicketList();
        SideMenu menu = new SideMenu(this, username);
        menuItems.add(menu.getMenu());
    }

    public void requestTicketList(){
        userModel.requestTicketList(username);
    }


    public ObservableValue<? extends String> showTicketProperty() {
        return callNewTicket;
    }

    public void resetTicketCall(){
        callNewTicket.setValue("");
    }
}
