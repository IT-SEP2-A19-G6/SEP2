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
    private IClientState currentState;
    private MenuViewModel menuViewModel;
    private TicketListViewModel ticketListViewModel;

    public ClientViewModel(MenuViewModel menuViewModel, TicketListViewModel ticketListViewModel) {
        this.menuViewModel = menuViewModel;
        this.ticketListViewModel = ticketListViewModel;
        setInitState();
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
        currentState.entry(menuViewModel, ticketListViewModel);
    }



}
