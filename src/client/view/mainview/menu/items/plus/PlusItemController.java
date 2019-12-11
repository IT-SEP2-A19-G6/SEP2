package client.view.mainview.menu.items.plus;

import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
