package client.viewmodel.client;

import client.model.ticketlist.ITicketListModel;
import client.util.ClientProperties;
import client.viewmodel.client.states.BranchMemberState;
import client.viewmodel.client.states.IClientState;
import client.viewmodel.client.states.UserState;
import client.viewmodel.menu.MenuViewModel;
import client.viewmodel.ticketlist.TicketListViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import shared.clients.ClientType;

public class ClientViewModel {
    private ITicketListModel ticketListModel;
    private ObservableList<Node> rightArea;
    private ObservableList<Node> menuItems;
    private IClientState currentState;
    private StringProperty callWindow; //TODO delete after incorporation of ticket
    private MenuViewModel menuViewModel;
    private TicketListViewModel ticketListViewModel;

    public ClientViewModel(ITicketListModel ticketListModel, MenuViewModel menuViewModel, TicketListViewModel ticketListViewModel) {
        this.ticketListModel = ticketListModel;
        this.menuViewModel = menuViewModel;
        this.ticketListViewModel = ticketListViewModel;
        rightArea = FXCollections.observableArrayList();
        menuItems = FXCollections.observableArrayList();
        callWindow = new SimpleStringProperty(); //TODO delete after incorporation of ticket
        setInitState();
    }

    private void setInitState(){
        callWindow.setValue("");
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
        currentState.entry(menuViewModel, ticketListViewModel);
    }

    public ObservableValue<? extends String> showTicketProperty() {
        return callWindow;
    }

    public ObservableList<Node> setRightArea(){
        return rightArea;
    }

    public ObservableList<Node> menuNodes(){
        return menuItems;
    }


    public void getMenu(){
        callWindow.setValue("show menu");
    }

    public void getTicketList() {
        callWindow.setValue("list tickets");
    }

    public void getBranchList() {
        callWindow.setValue("branch list");
    }

    public void createNewTicket() {
        callWindow.setValue("new ticket");
    }

    public void resetTicketCall(){
        callWindow.setValue("");
    }


}
