package client.view.ticketlist.items.replymessageitem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import shared.TicketReply;


public class ReplyMessageItemController {

    @FXML
    private Label labelId;
    @FXML
    private Label labelCreated;
    @FXML
    private Label labelCreatedBy;
    @FXML
    private Label labelMessage;

    public void init(TicketReply reply) {
        labelId.setText(String.valueOf(reply.getTicketId()));
        labelCreated.setText(reply.getTimeStamp());
        labelCreatedBy.setText(reply.getUsername());
        labelMessage.setText(reply.getMessage());
    }
}
