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


    @Override
    public void buildTicketList(ArrayList<Ticket> ticketsFromServer) {
        viewModel.clearRightArea();
        for (Ticket ticket : ticketsFromServer){
            TicketListItem listItem = new TicketListItem(viewModel, "id" + ticket.getSubject(), ticket.getDescription(), "SomeStatus", "SomeBranches", "SomeMember", ticket.getSubject(), "SomeTime", "SomeUpdate");
            ticketList.addTicketToList(listItem.getTicketListItem());
        }
        viewModel.buildRightArea(ticketList.getTicketList());
    }

    @Override
    public void showNoTickets() {
        viewModel.clearRightArea();
        viewModel.buildRightArea(ticketList.getEmptyList("No tickets yet - try add one..."));
    }

}
