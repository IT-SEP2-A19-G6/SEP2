package client.view.ticketlist;

import client.view.ticketlist.items.messageitem.MessageItemController;
import client.view.ticketlist.items.ticketitem.TicketItemController;
import client.viewmodel.ticketlist.TicketListViewModel;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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

//        ticketListViewModel.responseMessageProperty().addListener((observableValue, s, t1) -> {
//            if (!(t1.equals(""))){
//                Platform.runLater(() -> {
//                    ticketListVBox.getChildren().clear();
//                });
//                //createMessage(t1);
//                ticketListViewModel.resetResponseMessage();
//            }
//        });
    }

    private void createTicket(Ticket ticket) {
        Platform.runLater(() -> {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("items/ticketitem/TicketItemControl.fxml"));
            BorderPane pane  =  loader.load();
            TicketItemController controller = loader.getController();
            controller.init(ticket);
            ticketListVBox.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
        });
    }

//    private void createMessage() {
//        Platform.runLater(() -> {
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("items/messageitem/MessageItemControl.fxml"));
//                VBox messageBox  =  loader.load();
//                MessageItemController controller = loader.getController();
//                controller.init();
//                ticketListVBox.getChildren().add(messageBox);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }


    public void requestList(TicketListExchange ticketListExchange) {
        ticketListViewModel.requestTickets(ticketListExchange);
    }
}
