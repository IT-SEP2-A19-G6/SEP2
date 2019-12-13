package client.view.ticketlist.items.ticketitem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import shared.Ticket;

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



}
