package client.view.menu.icons.branch;

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

public class BranchItemController {
    @FXML
    Label label;

    private ClientViewModel clientViewModel;
    private TicketListViewModel ticketListViewModel;

    public void init(ViewModelFactory viewModelFactory, String branchName){
        this.clientViewModel = viewModelFactory.getClientViewModel();
        this.ticketListViewModel = viewModelFactory.getTicketListViewModel();
        label.setText(branchName);
    }

    public void doAction(ActionEvent actionEvent) {
        ticketListViewModel.requestTickets(new TicketListExchange(Request.TYPE.BRANCH_TICKET_LIST_REQ, ClientProperties.getInstance().getClient().getUsername()));
        clientViewModel.getTicketList();
    }


}
