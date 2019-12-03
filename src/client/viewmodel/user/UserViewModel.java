package client.viewmodel.user;

import client.model.user.IUserModel;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import shared.Request;
import shared.Ticket;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class UserViewModel {
    private IUserModel userModel;
    private StringProperty userLabelProperty;
    private ObservableList<Node> tickets;


    public UserViewModel(IUserModel userModel) {
        this.userModel = userModel;
        userLabelProperty = new SimpleStringProperty();
        tickets = FXCollections.observableArrayList();
        addListeners();

    }

    public static void getTicket(String id) {
        //TODO get exixting ticket by ID here
        System.out.println(id);
    }

    private void addListeners() {
        userModel.addPropertyChangeListener(Request.TYPE.TICKET_LIST_RESPONSE.name(), this::handleResponse);
    }

    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        ArrayList<Ticket> ticketsFromServer = (ArrayList<Ticket>) propertyChangeEvent.getNewValue();
        tickets.clear();
        for (Ticket ticket : ticketsFromServer){
            TicketItem item = new TicketItem();
            tickets.add(item.addTicketItem("id" + ticket.getSubject(), "SomeTime", "SomeUpdate", ticket.getDescription(), ticket.getTicketStatus().toString(), "SomeBranches", "SomeMember", ticket.getSubject()));
        }

    }

    public Property<String> userLabelProperty() {
        return userLabelProperty;
    }


    public ObservableList<Node> ticketNodes(){
        return tickets;
    }


    public void initView(String username) {
        userLabelProperty.setValue(username);
        userModel.requestTicketList(username);
    }

    public void addTicket() {
        userModel.addTicket();
    }
}
