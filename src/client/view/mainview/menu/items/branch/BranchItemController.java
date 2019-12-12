package client.view.mainview.menu.items.branch;

import client.util.ClientProperties;
import client.view.ViewHandler;
import client.view.mainview.menu.items.dotcontroller.IButtonController;
import client.view.mainview.menu.items.dotcontroller.IDotController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import shared.Request;
import shared.TicketListExchange;

public class BranchItemController implements IDotController {
    @FXML
    Label label;

    @FXML
    Circle dot;


    private IButtonController buttonController;

    public void init(String branchName, IButtonController buttonController){
        this.buttonController = buttonController;
        label.setText(branchName);
        setVisibility(false);
    }

    public void doAction(ActionEvent actionEvent) {
        ViewHandler.getInstance().loadTicketList(new TicketListExchange(Request.TYPE.BRANCH_TICKET_LIST_REQ, ClientProperties.getInstance().getClient().getUsername()));
        buttonController.branchButtonPressed(this);
    }

    @Override
    public void setVisibility(boolean bool) {
        dot.setVisible(bool);
    }
}
