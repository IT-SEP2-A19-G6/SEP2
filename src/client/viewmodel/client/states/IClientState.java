package client.viewmodel.client.states;

import client.viewmodel.client.ClientViewModel;
import shared.Ticket;

import java.util.ArrayList;

public interface IClientState {
    void entry(ClientViewModel ViewModel);
    void exit();

}
