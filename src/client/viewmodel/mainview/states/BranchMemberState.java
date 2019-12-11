package client.viewmodel.mainview.states;

import client.util.ClientProperties;
import client.viewmodel.mainview.MenuViewModel;


public class BranchMemberState implements IClientState {
    private MenuViewModel menuViewModel;


    @Override
    public void entry(MenuViewModel menuViewModel) {
        this.menuViewModel = menuViewModel;
        setMenu();
    }

    @Override
    public void setMenu() {
        menuViewModel.addClientIcon(true, false,  ClientProperties.getInstance().getClient().getUsername());
        menuViewModel.addPlusIcon(false, "New Ticket");
        menuViewModel.addBranchIcon(true, "Branch");
    }

    @Override
    public void exit() {
        menuViewModel.clearMenu();
    }

}
