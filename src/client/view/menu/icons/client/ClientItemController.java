package client.view.menu.icons.client;

import client.util.ClientProperties;
import client.view.ViewHandler;
import client.viewmodel.ViewModelFactory;
import client.viewmodel.client.ClientViewModel;
import client.viewmodel.ticketlist.TicketListViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import shared.Request;
import shared.TicketListExchange;

public class ClientItemController {
    @FXML
    Label label;

    private ClientViewModel clientViewModel;
    private TicketListViewModel ticketListViewModel;
    private boolean isUser;
    private String username;

    public void init(ViewModelFactory viewModelFactory, String username, boolean isUser){
        this.clientViewModel = viewModelFactory.getClientViewModel();
        this.ticketListViewModel = viewModelFactory.getTicketListViewModel();
        this.isUser = isUser;
        this.username = username;
        label.setText(username);
    }

    public void doAction(ActionEvent actionEvent) {
        if (isUser){
            ticketListViewModel.requestTickets(new TicketListExchange(Request.TYPE.OWN_TICKET_LIST_REQ, username));
        } else {
            System.out.println("member request own ticket");
            ticketListViewModel.requestTickets(new TicketListExchange(Request.TYPE.ASSIGNED_TICKET_LIST_REQ, username));
        }
        clientViewModel.getTicketList();
    }


}
