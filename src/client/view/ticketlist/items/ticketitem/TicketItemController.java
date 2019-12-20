package client.view.ticketlist.items.ticketitem;

import client.util.ClientProperties;
import client.view.ticketlist.items.replyitem.TicketReplyItemController;
import client.view.ticketlist.items.replymessageitem.ReplyMessageItemController;
import client.viewmodel.ViewModelFactory;
import client.viewmodel.communication.TicketReplyViewModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import shared.Request;
import shared.Ticket;
import shared.TicketReply;
import shared.clients.BranchMember;
import shared.clients.ClientType;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("SpellCheckingInspection")
public class TicketItemController {

    @FXML
    private Label labelId;
    @FXML
    private Label labelCreated;
    @FXML
    private Label labelCreatedBy;
    @FXML
    private Label labelLocation;
    @FXML
    private Label labelSubject;
    @FXML
    private Label labelStatus;
    @FXML
    private Label labelBranch;
    @FXML
    private Label labelAssignedTo;
    @FXML
    private VBox ticketVBox;
    @FXML
    private VBox branchMemberVBox;
    @FXML
    private ComboBox assigneeComboBox;
    @FXML
    private ComboBox statusComboBox;
    @FXML
    private Label labelDescription;

    private ArrayList<TicketReply> replies;
    private ViewModelFactory viewModelFactory;
    private TicketReplyViewModel viewModel;
    private Ticket ticket;
    private boolean isShown;
    private ArrayList<Node> replyNodes;
    private Node replyNode;


    public void init(ViewModelFactory viewModelFactory, Ticket ticket) {
        this.viewModelFactory = viewModelFactory;
        this.ticket = ticket;
        replyNodes = new ArrayList<>();


        viewModel = viewModelFactory.getTicketReplyViewModel();

        labelId.setText(String.valueOf(ticket.getId()));
        labelCreated.setText(ticket.getCreatedDate());
        labelCreatedBy.setText(ticket.getUsername());
        labelLocation.setText(ticket.getLocation());
        labelSubject.setText(ticket.getSubject());
        labelDescription.setText(ticket.getDescription());
        labelStatus.setText(ticket.getTicketStatus());
        labelBranch.setText(ticket.getBranch());
        labelAssignedTo.setText(ticket.getAssignee());
        isShown = false;

        viewModel.addPropertyChangeListener(Request.TYPE.TICKET_REPLY_RESPONSE.name() + ticket.getId(), this::handleReplies);

        replyNode = getReplyNode();


        if (ClientProperties.getInstance().getClient().getType() == ClientType.BRANCH_MEMBER) {
            setBranchOptions();
        } else {
            setUserOptions();
        }

    }

    private void handleReplies(PropertyChangeEvent propertyChangeEvent) {
        //noinspection unchecked
        replies = (ArrayList<TicketReply>) propertyChangeEvent.getNewValue();
        Platform.runLater(() -> {
            if (isShown)
                clearMessages();
            replyNodes.clear();
            for (TicketReply reply : replies) {
                replyNodes.add(createTicketReplyMessage(reply));
            }
            ticketVBox.getChildren().addAll(5, replyNodes);
            if (!ticketVBox.getChildren().contains(replyNode)) {
                ticketVBox.getChildren().add(replyNode);
            }
        });

    }

    public void showMoreButton() {
        if (isShown){
            clearMessages();
            isShown = false;
        } else {
            isShown = true;
            viewModel.getReplies(ticket.getId());
            ticketVBox.getChildren().add(replyNode);
        }
    }


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

    private void clearMessages(){
        ticketVBox.getChildren().removeAll(replyNodes);
        ticketVBox.getChildren().remove(replyNode);
    }

    @SuppressWarnings("unchecked")
    private void setBranchOptions() {
        branchMemberVBox.setVisible(true);
        branchMemberVBox.managedProperty().bind(branchMemberVBox.visibleProperty());
        assigneeComboBox.setItems(viewModelFactory.getTicketListViewModel().getBranchMembers());
        assigneeComboBox.getSelectionModel().select(labelAssignedTo.getText() == null ? "NONE" : labelAssignedTo.getText());
        statusComboBox.getSelectionModel().select(labelStatus.getText());
        viewModelFactory.getTicketListViewModel().requestBranchMembersByBranchName(ticket.getBranch());
    }

    private void setUserOptions() {
        // hides the space the vbox occupies by binding visibility properties
        branchMemberVBox.setVisible(false);
        branchMemberVBox.managedProperty().bind(branchMemberVBox.visibleProperty());
    }

    public void statusComboBoxAction() {
        String selectedValue = (String) statusComboBox.getValue();
        ticket.setTicketStatus(selectedValue);
        labelStatus.setText(selectedValue);
        viewModelFactory.getTicketListViewModel().setTicketStatus(ticket);

    }

    public void assigneeComboBoxAction() {
        BranchMember bm = (BranchMember) assigneeComboBox.getValue();

        if (bm != null) {
            viewModelFactory.getTicketListViewModel().setAssignee(bm.getUsername(), ticket.getId());
            labelAssignedTo.setText(bm.getUsername());
        }

    }
}
