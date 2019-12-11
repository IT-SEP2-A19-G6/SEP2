package client.view.mainview.menu.items.client;

import client.view.ViewHandler;
import client.view.mainview.menu.items.dotcontroller.DotHandler;
import client.view.mainview.menu.items.dotcontroller.IButtonController;
import client.view.mainview.menu.items.dotcontroller.IDotController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import shared.Request;
import shared.TicketListExchange;

public class ClientItemController implements IDotController {
    @FXML
    Label label;

    @FXML
    Circle dot;


    private boolean isUser;
    private String username;
    private ViewHandler viewHandler;
    private IButtonController buttonController;

    public void init(ViewHandler viewHandler, String username, boolean isUser, IButtonController buttonController){
        this.viewHandler = viewHandler;
        this.buttonController = buttonController;
        this.isUser = isUser;
        this.username = username;
        label.setText(username);
        setVisibility(true);
    }

    public void doAction(ActionEvent actionEvent) {
        buttonController.clientButtonPressed(this);
        if (isUser){
            viewHandler.loadTicketList(new TicketListExchange(Request.TYPE.OWN_TICKET_LIST_REQ, username));
        } else {
            viewHandler.loadTicketList(new TicketListExchange(Request.TYPE.ASSIGNED_TICKET_LIST_REQ, username));
        }
    }

    @Override
    public void setVisibility(boolean bool) {
        dot.setVisible(bool);
    }
}
