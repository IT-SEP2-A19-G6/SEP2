package client.view.client;

import client.view.ViewHandler;
import client.viewmodel.client.ClientViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class ClientViewController {

    @FXML
    AnchorPane menuPane, rightArea;

    ViewHandler vh;


    public void init(ViewHandler viewHandler, ClientViewModel userViewModel){
        vh = viewHandler;
        Bindings.bindContentBidirectional(userViewModel.setRightArea(), rightArea.getChildren());
        Bindings.bindContentBidirectional(userViewModel.menuNodes(), menuPane.getChildren());
        userViewModel.showTicketProperty().addListener((observableValue, s, t1) -> {
            if(t1.equals("new ticket")){
//                viewHandler.openCreateTicketView();
                userViewModel.resetTicketCall();
                setCurrentContent();
            }
        });
    }



    private void setCurrentContent() {

        rightArea.getChildren().clear();
        //rightArea.getChildren().add(vh.loadCreateTicketView());
        rightArea.getChildren().addAll(vh.loadTicketList());

        System.out.println("clicked");

    }

}
