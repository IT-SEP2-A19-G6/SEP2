package client.view.ticketlist.items.ticketitem;

import client.view.ticketlist.items.messageitem.MessageItemController;
import client.view.ticketlist.items.replyitem.TicketReplyItemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import shared.Ticket;
import shared.TicketReply;

import java.io.IOException;
import java.util.ArrayList;

public class TicketItemController {

    @FXML
    public Label labelId;
    @FXML
    public Label labelCreated;
    @FXML
    public Label labelCreatedBy;
    @FXML
    public Label labelLocation;
    @FXML
    public Label labelSubject;
    @FXML
    public Label labelStatus;
    @FXML
    public Label labelBranch;
    @FXML
    public Label labelAssignedTo;
    @FXML
    public VBox ticketVBox;



    public void init(Ticket ticket) {



        labelId.setText(String.valueOf(ticket.getId()));
        labelCreated.setText(ticket.getCreatedDate());
        labelCreatedBy.setText(ticket.getUsername());
        labelLocation.setText(ticket.getLocation());
        labelSubject.setText(ticket.getSubject());
        labelStatus.setText(ticket.getTicketStatus());
        labelBranch.setText(ticket.getBranch());
        labelAssignedTo.setText(ticket.getAssignee());
    }

    public void showMoreButton(ActionEvent actionEvent) {


        if (ticketVBox.getChildren().size() > 4) {
            for (int i = 0; i < ticketVBox.getChildren().size(); i++) {
                ticketVBox.getChildren().remove(4);
            }
        } else {
            loadReplies();
        }

    }


    private void loadReplies() {
        //Dummy replies
        replies.clear();
        replies.add(new TicketReply("Test 1", "user1", 1));
        replies.add(new TicketReply("Test 2", "user1", 1));
        replies.add(new TicketReply("Test 3", "user1", 1));
        replies.add(new TicketReply("Test 4", "user1", 1));

        for (TicketReply reply : replies) {
            ticketVBox.getChildren().add(createTicketReplyMessage(reply));
        }

        ticketVBox.getChildren().add(getReplyNode());
    }

    //Set the replies into this arraylist.
    ArrayList<TicketReply> replies = new ArrayList<>();

    private Node getReplyNode() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../items/replyitem/TicketReplyItemControl.fxml"));
            BorderPane pane  =  loader.load();
            TicketReplyItemController controller = loader.getController();
            controller.init();
            return pane;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Node createTicketReplyMessage(TicketReply reply) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../items/messageitem/MessageItemControl.fxml"));
            BorderPane pane  =  loader.load();
            MessageItemController controller = loader.getController();
            controller.init(reply);
            return pane;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
