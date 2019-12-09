package client.view.menu.icons.plus;

import client.view.ViewHandler;
import client.viewmodel.ViewModelFactory;
import client.viewmodel.client.ClientViewModel;
import client.viewmodel.ticketlist.TicketListViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class PlusItemController {
    @FXML
    Label label;

    private ClientViewModel clientViewModel;
    private TicketListViewModel ticketListViewModel;

    public void init(ViewModelFactory viewModelFactory, String iconText){
        this.clientViewModel = viewModelFactory.getClientViewModel();
        this.ticketListViewModel = viewModelFactory.getTicketListViewModel();
        label.setText(iconText);
    }

    public void doAction(ActionEvent actionEvent) {
        clientViewModel.createNewTicket();
    }

}
