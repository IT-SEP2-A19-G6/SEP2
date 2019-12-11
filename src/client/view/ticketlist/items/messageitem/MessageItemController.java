package client.view.ticketlist.items.messageitem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class MessageItemController {
    @FXML
    Label messageLabel;

    public void init(String message){
        messageLabel.setText(message);
    }
}
