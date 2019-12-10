package client.viewmodel.mainview.states;

import client.viewmodel.mainview.MenuViewModel;

public interface IClientState {
    void entry(MenuViewModel menuViewModel);
    void exit();
    void setMenu();

}
