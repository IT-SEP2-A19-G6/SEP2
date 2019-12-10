package client.viewmodel.client.states;

import client.util.ClientProperties;
import client.viewmodel.client.ClientViewModel;
import client.viewmodel.menu.MenuViewModel;
import client.viewmodel.ticketlist.TicketListViewModel;
import shared.Request;
import shared.TicketListExchange;


public class UserState implements IClientState {
    private MenuViewModel menuViewModel;

    @Override
    public void entry(MenuViewModel menuViewModel, TicketListViewModel ticketListViewModel) {
        this.menuViewModel = menuViewModel;
        setMenu();
    }

    private void setMenu() {
        menuViewModel.addClientIcon(true, true,  ClientProperties.getInstance().getClient().getUsername());
        menuViewModel.addPlusIcon(true, "New Ticket");
        menuViewModel.addBranchIcon(false, "Branch");
    }

    @Override
    public void exit() {
        menuViewModel.clearMenu();
    }


}
