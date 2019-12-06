package client.viewmodel.client.states;

import client.viewmodel.client.ClientViewModel;
import client.viewmodel.client.uielements.SideMenu;
import client.viewmodel.client.uielements.TicketList;
import client.viewmodel.client.uielements.TicketListItem;
import shared.Ticket;

import java.util.ArrayList;

public class UserState implements IClientState {
    private ClientViewModel viewModel;
    private TicketList ticketList;
    private SideMenu menu;


    @Override
    public void entry(ClientViewModel viewModel) {
        this.viewModel = viewModel;
        this.ticketList = new TicketList();
        this.menu = new SideMenu(viewModel);
        menu.setTicketListVisibility(true);
        menu.setNewTicketVisibility(true);
        viewModel.requestOwnTicketList();
        viewModel.buildMenu(menu.getMenu());
    }

    @Override
    public void exit() {
        viewModel.clearMenu();
        viewModel.clearRightArea();
    }


}
