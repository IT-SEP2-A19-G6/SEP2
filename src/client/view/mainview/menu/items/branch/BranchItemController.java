package client.view.mainview.menu.items.branch;

import client.util.ClientProperties;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import shared.Request;
import shared.TicketListExchange;

public class BranchItemController {
    @FXML
    Label label;

    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, String branchName){
        this.viewHandler = viewHandler;
        label.setText(branchName);
    }

    public void doAction(ActionEvent actionEvent) {
        viewHandler.loadTicketList(new TicketListExchange(Request.TYPE.BRANCH_TICKET_LIST_REQ, ClientProperties.getInstance().getClient().getUsername()));
    }


}
