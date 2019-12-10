package client.view.mainview.menu.items.client;

import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import shared.Request;
import shared.TicketListExchange;

public class ClientItemController {
    @FXML
    Label label;


    private boolean isUser;
    private String username;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, String username, boolean isUser){
        this.viewHandler = viewHandler;
        this.isUser = isUser;
        this.username = username;
        label.setText(username);
    }

    public void doAction(ActionEvent actionEvent) {
        if (isUser){
            viewHandler.loadTicketList(new TicketListExchange(Request.TYPE.OWN_TICKET_LIST_REQ, username));
        } else {
            viewHandler.loadTicketList(new TicketListExchange(Request.TYPE.ASSIGNED_TICKET_LIST_REQ, username));
        }
    }


}
