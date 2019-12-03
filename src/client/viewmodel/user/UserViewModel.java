package client.viewmodel.user;

import client.model.user.IUserModel;
import client.viewmodel.user.uielements.SideMenu;
import client.viewmodel.user.uielements.TicketList;
import client.viewmodel.user.uielements.TicketListItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import shared.Request;
import shared.Ticket;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class UserViewModel {
    private IUserModel userModel;
    private ObservableList<Node> rightArea;
    private ObservableList<Node> menuItems;
    private String username;


    public UserViewModel(IUserModel userModel) {
        this.userModel = userModel;
        rightArea = FXCollections.observableArrayList();
        menuItems = FXCollections.observableArrayList();
        addListeners();
    }

    private void addListeners() {
        userModel.addPropertyChangeListener(Request.TYPE.TICKET_LIST_RESPONSE.name(), this::handleResponse);
    }

    public  void getTicket(String id) {
        //TODO get exixting ticket by ID here
        System.out.println(id);
    }

    public void getTicketList() {
        //TODO call ticketList
    }

    //TODO delete or use
    public void addNewTicket() {
//        userModel.addTicket();
    }



    private void handleResponse(PropertyChangeEvent propertyChangeEvent) {
        ArrayList<Ticket> ticketsFromServer = (ArrayList<Ticket>) propertyChangeEvent.getNewValue();
        rightArea.clear();
        TicketList ticketList = new TicketList();
        for (Ticket ticket : ticketsFromServer){
            TicketListItem listItem = new TicketListItem(this, "id" + ticket.getSubject(), ticket.getDescription(), ticket.getTicketStatus().toString(), "SomeBranches", "SomeMember", ticket.getSubject(), "SomeTime", "SomeUpdate");
            ticketList.addTicketToList(listItem.getTicketListItem());
        }
        rightArea.add(ticketList.getTicketList());
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

}
