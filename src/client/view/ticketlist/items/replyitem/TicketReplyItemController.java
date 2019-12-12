package client.view.ticketlist.items.replyitem;

import client.view.ticketlist.items.messageitem.MessageItemController;
import client.view.ticketlist.items.ticketitem.TicketItemController;
import client.viewmodel.communication.TicketReplyViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import shared.TicketReply;

import java.io.IOException;
import java.util.ArrayList;

public class TicketReplyItemController {

    @FXML
    public Label messagesLabel;
    @FXML
    public TextArea messageTextArea;
    @FXML
    public Label textAreaEmpty;

    private TicketReplyViewModel viewModel;
    private TicketItemController ticketItemController;

    public void openMessage() {

    }



    public void init() {
       // messageTextArea.textProperty().bindBidirectional(viewModel.messageAreProperty());
    }



    public void submitButton(ActionEvent actionEvent) {

        //TODO make a label saying the textarea is empty.
        if(messageTextArea.getText().isEmpty()) return;

       // viewModel.submitReply();
    }


//    public void init(Arraylist<Reply> replies) {
//
//    }

}
