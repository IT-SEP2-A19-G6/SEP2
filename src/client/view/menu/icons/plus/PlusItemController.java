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

    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, String iconText){
        this.viewHandler = viewHandler;
        label.setText(iconText);
    }

    public void doAction(ActionEvent actionEvent) {
        viewHandler.loadCreateTicket();
    }

}
