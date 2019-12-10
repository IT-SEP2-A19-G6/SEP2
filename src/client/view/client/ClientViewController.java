package client.view.client;


import client.util.ClientProperties;
import client.view.ViewHandler;
import client.viewmodel.client.ClientViewModel;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import shared.Request;
import shared.TicketListExchange;
import shared.clients.ClientType;

public class ClientViewController {

    @FXML
    AnchorPane menuPane, rightArea;

    private ViewHandler vh;


    public void init(ViewHandler viewHandler, ClientViewModel clientViewModel){
        this.vh = viewHandler;
        setContentCreateMenu();
        setContentListTickets();
    }

    private void setContentCreateMenu() {
        vh.loadMenu();
    }


    private void setContentListTickets() {
        if (ClientProperties.getInstance().getClient().getType().equals(ClientType.USER)){
            vh.loadTicketList(new TicketListExchange(Request.TYPE.OWN_TICKET_LIST_REQ, ClientProperties.getInstance().getClient().getUsername()));
        } else {
            vh.loadTicketList(new TicketListExchange(Request.TYPE.ASSIGNED_TICKET_LIST_REQ, ClientProperties.getInstance().getClient().getUsername()));

        }
    }

    public void setRightArea(Node node) {
        rightArea.getChildren().clear();
        rightArea.getChildren().add(node);
    }

    public void setMenu(Node node){
        menuPane.getChildren().clear();
        menuPane.getChildren().add(node);
    }
}
