package client.view.ticketlist.items.replyitem;

import client.viewmodel.communication.TicketReplyViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


public class TicketReplyItemController {

    @FXML
    public Label messagesLabel;
    @FXML
    private TextArea messageTextArea;
    @FXML
    public Label textAreaEmpty;

    private TicketReplyViewModel viewModel;

    private int id;

    public void init(TicketReplyViewModel vm, int id) {
       viewModel = vm;
       this.id = id;
       messageTextArea.textProperty().bindBidirectional(viewModel.messageAreaProperty());
    }

    public void submitButton() {
        if(messageTextArea.getText().isEmpty()) return;

        viewModel.addReply(id);
        viewModel.getReplies(id);
        messageTextArea.clear();
    }

}
