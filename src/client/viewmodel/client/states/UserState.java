package client.viewmodel.client.states;

import client.viewmodel.client.ClientViewModel;
import client.viewmodel.client.uielements.Menu;
import client.viewmodel.client.uielements.TicketList;
import javafx.scene.Node;

import java.util.ArrayList;

public class UserState implements IClientState {
    private ClientViewModel viewModel;
    private TicketList ticketList;
    private Menu menu;


    @Override
    public void entry(ClientViewModel viewModel) {
        this.viewModel = viewModel;
        this.ticketList = new TicketList();
        this.menu = new Menu(viewModel);
        viewModel.requestOwnTicketList();
        viewModel.buildMenu(menu.createMenu(buildMenuItems()));
    }

    @Override
    public void exit() {
        viewModel.clearMenu();
        viewModel.clearRightArea();
    }

    private ArrayList<Node> buildMenuItems(){
        ArrayList<Node> menuItems = new ArrayList<>();
        menuItems.add(menu.addClientListItem());
        menuItems.add(menu.addNewTicketItem());
        return menuItems;
    }


}
