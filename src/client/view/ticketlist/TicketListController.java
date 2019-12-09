package client.view.ticketlist;

import client.view.items.ticket.TicketItemController;
import client.viewmodel.ticketlist.TicketListViewModel;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import shared.Ticket;

import java.io.IOException;
import java.util.ArrayList;

public class TicketListController {

    @FXML
    VBox ticketListVBox;

    private TicketListViewModel ticketListViewModel;

    private ObservableList<Ticket> tickets;
    private ArrayList<Ticket> ticketArrayList;

    public void init(TicketListViewModel ticketListViewModel) {
        this.ticketListViewModel = ticketListViewModel;
        ticketArrayList = new ArrayList<>();

        // bind ticket list
        tickets = FXCollections.observableArrayList(new ArrayList<>());
        Bindings.bindContentBidirectional(ticketListViewModel.getTickets(), tickets);


        tickets.addListener((ListChangeListener.Change<? extends Ticket> c) -> {
            Platform.runLater(() -> {
                ticketListVBox.getChildren().clear();
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
        });
    }


    private void createTicket(Ticket ticket) {
        System.out.println("displaying new tickets");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../items/ticket/TicketItemControl.fxml"));
            Pane pane  =  loader.load();
            TicketItemController controller = loader.getController();
            controller.init(ticket);
            ticketListVBox.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
