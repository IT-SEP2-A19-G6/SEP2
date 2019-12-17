package client.view.mainview;


import client.util.ClientProperties;
import client.view.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import shared.Request;
import shared.TicketListExchange;
import shared.clients.ClientType;

public class MainViewController {

    @FXML
    AnchorPane menuPane, rightArea;



    public void init(){
        setContentCreateMenu();
        setContentListTickets();
    }

    private void setContentCreateMenu() {
        ViewHandler.getInstance().loadMenu();
    }


    private void setContentListTickets() {
        if (ClientProperties.getInstance().getClient().getType().equals(ClientType.USER)){
            ViewHandler.getInstance().loadTicketList(new TicketListExchange(Request.TYPE.OWN_TICKET_LIST_REQ, ClientProperties.getInstance().getClient()));
        } else {
            ViewHandler.getInstance().loadTicketList(new TicketListExchange(Request.TYPE.ASSIGNED_TICKET_LIST_REQ, ClientProperties.getInstance().getClient()));
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
