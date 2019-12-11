package client.viewmodel.mainview.states;

import client.util.ClientProperties;
import client.viewmodel.mainview.MenuViewModel;


public class UserState implements IClientState {
    private MenuViewModel menuViewModel;

    @Override
    public void entry(MenuViewModel menuViewModel) {
        this.menuViewModel = menuViewModel;
        setMenu();
    }

    @Override
    public void setMenu() {
        menuViewModel.addClientIcon(true, true,  ClientProperties.getInstance().getClient().getUsername());
        menuViewModel.addPlusIcon(true, "New Ticket");
        menuViewModel.addBranchIcon(false, "Branch");
    }

    @Override
    public void exit() {
        menuViewModel.clearMenu();
    }


}
