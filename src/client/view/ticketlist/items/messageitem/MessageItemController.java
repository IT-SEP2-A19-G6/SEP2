package client.view.ticketlist.items.messageitem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import shared.Ticket;
import shared.TicketReply;


public class MessageItemController {

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
