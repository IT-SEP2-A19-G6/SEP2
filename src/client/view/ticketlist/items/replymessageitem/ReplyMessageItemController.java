package client.view.ticketlist.items.replymessageitem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import shared.TicketReply;


public class ReplyMessageItemController {

    @FXML
    public Label labelId;
    @FXML
    public Label labelCreated;
    @FXML
    public Label labelCreatedBy;
    @FXML
    public Label labelMessage;

    public void init(TicketReply reply) {
        labelId.setText(String.valueOf(reply.getTicketId()));
        labelCreated.setText(String.valueOf(reply.getTimeStamp()));
        labelCreatedBy.setText(reply.getUsername());
        labelMessage.setText(reply.getMessage());
    }
}
