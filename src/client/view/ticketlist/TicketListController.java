package client.view.ticketlist;

import client.view.ticketlist.listitem.TicketItemController;
import client.viewmodel.ticketlist.TicketListViewModel;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import shared.Ticket;
import shared.TicketListExchange;

import java.io.IOException;
;

public class TicketListController {

    @FXML
    VBox ticketListVBox;

    @FXML
    AnchorPane pane;

    private TicketListViewModel ticketListViewModel;

    public void init(TicketListViewModel ticketListViewModel) {
        this.ticketListViewModel = ticketListViewModel;

        ticketListViewModel.getTickets().addListener((ListChangeListener.Change<? extends Ticket> c) -> {

            Platform.runLater(() -> {
                ticketListVBox.getChildren().clear();
            });
            while (c.next()) {
                if (c.wasAdded()) {
                    int start = c.getFrom();
                    int end = c.getTo();
                    for (int i = start; i < end; i++) {
                        Ticket newTicket = c.getList().get(i);
                        createTicket(newTicket);
                    }
                }
            }


        });
    }

    private void createTicket(Ticket ticket) {
        Platform.runLater(() -> {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listitem/TicketItemControl.fxml"));
            BorderPane pane  =  loader.load();
            TicketItemController controller = loader.getController();
            controller.init(ticket);
            ticketListVBox.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
        });

    }


    public void requestList(TicketListExchange ticketListExchange) {
        ticketListViewModel.requestTickets(ticketListExchange);
    }
}
