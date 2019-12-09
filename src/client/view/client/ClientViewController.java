package client.view.client;

import client.model.ModelFactory;
import client.view.ViewHandler;
import client.view.menu.MenuViewController;
import client.viewmodel.ViewModelFactory;
import client.viewmodel.client.ClientViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class ClientViewController {

    @FXML
    AnchorPane menuPane, rightArea;

    private ViewHandler vh;


    public void init(ViewHandler viewHandler, ClientViewModel clientViewModel){
        this.vh = viewHandler;
        Bindings.bindContentBidirectional(clientViewModel.setRightArea(), rightArea.getChildren());
        Bindings.bindContentBidirectional(clientViewModel.menuNodes(), menuPane.getChildren());
        clientViewModel.showTicketProperty().addListener((observableValue, s, t1) -> {
            if(t1.equals("new ticket")){
                clientViewModel.resetTicketCall();
                setContentCreateTicket();
            } else if (t1.equals("list tickets")) {
                clientViewModel.resetTicketCall();
                setContentListTickets();
            } else if (t1.equals("show menu")){
                clientViewModel.resetTicketCall();
                setContentCreateMenu();
            } else if (t1.equals("branch list")){
                clientViewModel.resetTicketCall();
                System.out.println("Branch list called");
            }
        });
        setContentCreateMenu();
        setContentListTickets();
    }

    private void setContentCreateMenu() {
        menuPane.getChildren().clear();
        menuPane.getChildren().add(vh.loadMenu());
    }

    private void setContentCreateTicket() {
        rightArea.getChildren().clear();
        rightArea.getChildren().add(vh.loadCreateTicket());
    }

    private void setContentListTickets() {
        rightArea.getChildren().clear();
        rightArea.getChildren().add(vh.loadTicketList());
    }



}
