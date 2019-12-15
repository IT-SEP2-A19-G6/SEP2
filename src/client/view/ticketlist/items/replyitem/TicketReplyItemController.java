package client.view.ticketlist.items.replyitem;

import client.viewmodel.communication.TicketReplyViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


public class TicketReplyItemController {

    @FXML
    public Label messagesLabel;
    @FXML
    public TextArea messageTextArea;
    @FXML
    public Label textAreaEmpty;

    private TicketReplyViewModel viewModel;
    private StringProperty replyResult;
    private int id;

    public void init(TicketReplyViewModel vm, int id) {
       viewModel = vm;
       this.id = id;
       messageTextArea.textProperty().bindBidirectional(viewModel.messageAreaProperty());
       replyResult = new SimpleStringProperty();
       replyResult.bind(viewModel.replyResultProperty());
    }

    public void submitButton(ActionEvent actionEvent) {

        //TODO make a label saying the textarea is empty.
        if(messageTextArea.getText().isEmpty()) return;

        viewModel.addReply(id);
        viewModel.getReplies(id);
        messageTextArea.clear();
    }

}
