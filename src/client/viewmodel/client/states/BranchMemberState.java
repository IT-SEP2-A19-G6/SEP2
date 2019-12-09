package client.viewmodel.client.states;

import client.util.ClientProperties;
import client.viewmodel.client.ClientViewModel;
import client.viewmodel.menu.MenuViewModel;
import client.viewmodel.ticketlist.TicketListViewModel;
import shared.Request;
import shared.TicketListExchange;


public class BranchMemberState implements IClientState {
    private MenuViewModel menuViewModel;


    @Override
    public void entry(MenuViewModel menuViewModel, TicketListViewModel ticketListViewModel) {
        this.menuViewModel = menuViewModel;
        ticketListViewModel.requestTickets(new TicketListExchange(Request.TYPE.ASSIGNED_TICKET_LIST_REQ, ClientProperties.getInstance().getClient().getUsername()));
        setMenu();
    }

    private void setMenu() {
        menuViewModel.addClientIcon(true, true,  ClientProperties.getInstance().getClient().getUsername());
        menuViewModel.addPlusIcon(false, "New Ticket");
        menuViewModel.addBranchIcon(true, "Branch");
    }

    @Override
    public void exit() {
        menuViewModel.clearMenu();
    }

}
