package client.view.user;

import client.view.ViewHandler;
import client.viewmodel.user.UserViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class UserViewController {

    @FXML
    AnchorPane menuPane, rightArea;

    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, UserViewModel userViewModel, String username){
        this.viewHandler = viewHandler;
        Bindings.bindContentBidirectional(userViewModel.setRightArea(), rightArea.getChildren());
        Bindings.bindContentBidirectional(userViewModel.menuNodes(), menuPane.getChildren());
        userViewModel.initView(username);
    }



}
