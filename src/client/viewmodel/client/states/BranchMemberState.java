package client.viewmodel.client.states;

import client.viewmodel.client.ClientViewModel;
import client.viewmodel.client.uielements.SideMenu;
import client.viewmodel.client.uielements.TicketList;
import client.viewmodel.client.uielements.TicketListItem;
import shared.Ticket;

import java.util.ArrayList;

public class BranchMemberState implements IClientState {
    private ClientViewModel viewModel;
    private TicketList ticketList;
    private SideMenu menu;

    @Override
    public void entry(ClientViewModel viewModel) {
        this.viewModel = viewModel;
        this.ticketList = new TicketList();
        this.menu = new SideMenu(viewModel);
        this.menu.setTicketListVisibility(true);
        this.menu.setNewTicketVisibility(false);
        viewModel.requestAssignedTicketList();
        viewModel.buildMenu(menu.getMenu());
    }

    @Override
    public void exit() {
        viewModel.clearMenu();
        viewModel.clearRightArea();
    }

}
