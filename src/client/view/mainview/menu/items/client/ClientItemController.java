package client.view.mainview.menu.items.client;

import client.view.ViewHandler;
import client.view.mainview.menu.items.IVirtualButton;
import client.view.mainview.menu.items.dotcontroller.IButtonController;
import client.view.mainview.menu.items.dotcontroller.IDotController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import shared.Request;
import shared.TicketListExchange;

public class ClientItemController implements IDotController, IVirtualButton {
    @FXML
    Label label;

    @FXML
    Circle dot;


    private boolean isUser;
    private String username;

    private IButtonController buttonController;

    public void init(String username, boolean isUser, IButtonController buttonController){
        this.buttonController = buttonController;
        this.isUser = isUser;
        this.username = username;
        label.setText(username);
        setVisibility(true);
    }

    public void doAction(ActionEvent actionEvent) {
       doButtonAction();
    }

    @Override
    public void setVisibility(boolean bool) {
        dot.setVisible(bool);
    }

    @Override
    public void pressButton() {
        doButtonAction();
    }

    public void doButtonAction(){
        buttonController.clientButtonPressed(this);
        if (isUser){
            ViewHandler.getInstance().loadTicketList(new TicketListExchange(Request.TYPE.OWN_TICKET_LIST_REQ, username));
        } else {
            ViewHandler.getInstance().loadTicketList(new TicketListExchange(Request.TYPE.ASSIGNED_TICKET_LIST_REQ, username));
        }
    }
}
