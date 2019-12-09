package client.view.client;

import client.view.ViewHandler;
import client.view.menu.MenuViewController;
import client.viewmodel.client.ClientViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class ClientViewController {

    @FXML
    AnchorPane menuPane, rightArea;

    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, ClientViewModel userViewModel){
        this.viewHandler = viewHandler;
        Bindings.bindContentBidirectional(userViewModel.setRightArea(), rightArea.getChildren());
        Bindings.bindContentBidirectional(userViewModel.menuNodes(), menuPane.getChildren());
        userViewModel.showTicketProperty().addListener((observableValue, s, t1) -> {
            if(t1.equals("new ticket")){
                userViewModel.resetTicketCall();
                setCurrentContent();
            }
        });
    }



    private void setCurrentContent() {
        rightArea.getChildren().clear();
        //rightArea.getChildren().add(vh.loadCreateTicketView());
        rightArea.getChildren().addAll(viewHandler.loadTicketList());

        System.out.println("clicked");


    }

}
