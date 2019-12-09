package client.viewmodel.client.states;

import client.viewmodel.client.ClientViewModel;
import client.viewmodel.menu.MenuViewModel;
import client.viewmodel.ticketlist.TicketListViewModel;

public interface IClientState {
    void entry(MenuViewModel menuViewModel, TicketListViewModel ticketListViewModel);
    void exit();

}
