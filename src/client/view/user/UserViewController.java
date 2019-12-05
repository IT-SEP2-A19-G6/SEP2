package client.view.user;

import client.view.ViewHandler;
import client.viewmodel.user.UserViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class UserViewController {

    @FXML
    AnchorPane menuPane, rightArea;


    public void init(ViewHandler viewHandler, UserViewModel userViewModel, String username){
        Bindings.bindContentBidirectional(userViewModel.setRightArea(), rightArea.getChildren());
        Bindings.bindContentBidirectional(userViewModel.menuNodes(), menuPane.getChildren());
        userViewModel.initView(username);
        userViewModel.showTicketProperty().addListener((observableValue, s, t1) -> {
            if(t1.equals("new ticket")){
                viewHandler.openCreateTicketView();
                userViewModel.resetTicketCall();
            }
        });
    }

}
