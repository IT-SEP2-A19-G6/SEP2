package client.view.ticketlist.items.ticketitem;

import client.view.ticketlist.items.replymessageitem.ReplyMessageItemController;
import client.view.ticketlist.items.replyitem.TicketReplyItemController;
import client.viewmodel.ViewModelFactory;
import client.viewmodel.communication.TicketReplyViewModel;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import shared.Request;
import shared.Ticket;
import shared.TicketReply;

import java.beans.PropertyChangeEvent;
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

    ArrayList<TicketReply> replies;
    private ViewModelFactory viewModelFactory;
    private TicketReplyViewModel viewModel;
    private Ticket ticket;
    private boolean isShown;



    public void init(ViewModelFactory viewModelFactory, Ticket ticket) {
        this.viewModelFactory = viewModelFactory;
        this.ticket = ticket;
        this.viewModel = viewModelFactory.getTicketReplyViewModel();
        labelId.setText(String.valueOf(ticket.getId()));
        labelCreated.setText(ticket.getCreatedDate());
        labelCreatedBy.setText(ticket.getUsername());
        labelLocation.setText(ticket.getLocation());
        labelSubject.setText(ticket.getSubject());
        labelStatus.setText(ticket.getTicketStatus());
        labelBranch.setText(ticket.getBranch());
        labelAssignedTo.setText(ticket.getAssignee());
        isShown = false;

        viewModel.addPropertyChangeListener(Request.TYPE.TICKET_REPLY_RESPONSE.name() + ticket.getId(), this::handleReplies);



//        viewModel.getTicketReplies().addListener((ListChangeListener.Change<? extends TicketReply> c) -> {
//            while (c.next()) {
//                if (c.wasAdded()) {
//                    int start = c.getFrom();
//                    int end = c.getTo();
//                    for (int i = start; i < end; i++) {
//                        TicketReply newTicketReply = c.getList().get(i);
//                        if (newTicketReply.getTicketId() == ticket.getId()){
//                            replies.add(newTicketReply);
//                        }
//                    }
//                }
//            }
//            Platform.runLater(() -> {
//                for (TicketReply reply : replies) {
//                    ticketVBox.getChildren().add(createTicketReplyMessage(reply));
//                }
//                ticketVBox.getChildren().add(getReplyNode());
//            });
//        });
    }

    private void handleReplies(PropertyChangeEvent propertyChangeEvent) {
        replies = (ArrayList<TicketReply>) propertyChangeEvent.getNewValue();
        Platform.runLater(() -> {
            clearMessages(4);
            for (TicketReply reply : replies) {
                ticketVBox.getChildren().add(createTicketReplyMessage(reply));
            }
            ticketVBox.getChildren().add(getReplyNode());
            System.out.println("After adding: " + ticketVBox.getChildren().size());
        });

    }

    public void showMoreButton(ActionEvent actionEvent) {
        if (isShown){
            System.out.println("shown true");
            clearMessages(4);
            isShown = false;
        } else {
            isShown = true;
            viewModel.getReplies(ticket.getId());
            ticketVBox.getChildren().add(getReplyNode());
        }
//
//        //Change if necessary
//        // TODO fix check...
//        if (ticketVBox.getChildren().size() > 4) {
//            clearMessages(4);
//        } else {
////            viewModel.getTicketReplies().clear();
////            loadReplies();
//        }
    }

//    private void loadReplies() {
//        replies.clear();
//
//        viewModel.getTicketReplies().addListener((ListChangeListener.Change<? extends TicketReply> c) -> {
//            while (c.next()) {
//                if (c.wasAdded()) {
//                    int start = c.getFrom();
//                    int end = c.getTo();
//                    for (int i = start; i < end; i++) {
//                        TicketReply newTicketReply = c.getList().get(i);
//                        replies.add(newTicketReply);
//                    }
//                }
//            }
//            Platform.runLater(() -> {
//                for (TicketReply reply : replies) {
//                    ticketVBox.getChildren().add(createTicketReplyMessage(reply));
//                }
//                ticketVBox.getChildren().add(getReplyNode());
//            });
//        });;
//
//    }

    private Node getReplyNode() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../items/replyitem/TicketReplyItemControl.fxml"));
            BorderPane pane  =  loader.load();
            TicketReplyItemController controller = loader.getController();
            controller.init(viewModelFactory.getTicketReplyViewModel(), ticket.getId());
            return pane;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Node createTicketReplyMessage(TicketReply reply) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../replymessageitem/ReplyMessageItemControl.fxml"));
            BorderPane pane  =  loader.load();
            ReplyMessageItemController controller = loader.getController();
            controller.init(reply);
            return pane;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void clearMessages(int from){
        System.out.println("Before clear: " + ticketVBox.getChildren().size());
        for (int i = 0; i < ticketVBox.getChildren().size(); i++) {
            if (ticketVBox.getChildren().size() > 4)
                ticketVBox.getChildren().remove(from);
//                ticketVBox.getChildren().remove(ticketVBox.getChildren().size() - 1);
        }
        System.out.println("After clear: " + ticketVBox.getChildren().size());
    }

}
