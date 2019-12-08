package client.view.ticketlist;

import client.view.items.ticket.TicketItemController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import shared.Ticket;

import java.io.IOException;

public class TicketListController {

    @FXML
    public AnchorPane anchorPane;
    @FXML
    VBox ticketListVBox;

    public void init() {
        loadTickets();


    }



    private void loadTickets() {
        for (int i = 0; i < 10 ; i++) {
            Ticket ticket = new Ticket(i, "now", "me", "Test " + i, "short desc", "Category 1", "location 1", "OPEN", "IT Branch", "Me");
            ticketListVBox.getChildren().add(createTicket(ticket));
        }
    }


    private Pane createTicket(Ticket ticket) {
        Pane content = new Pane();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../items/ticket/TicketItemControl.fxml"));
            Pane pane  =  loader.load();
            TicketItemController controller = loader.getController();
            controller.init(ticket);
            content = pane;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

}
