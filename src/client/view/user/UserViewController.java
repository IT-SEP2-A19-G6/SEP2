package client.view.user;

import client.view.ViewHandler;
import client.viewmodel.user.UserViewModel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



public class UserViewController {

    @FXML
    AnchorPane menuPane, rightArea;


    private ViewHandler viewHandler;
    private UserViewModel userViewModel;

    public void init(ViewHandler viewHandler, UserViewModel userViewModel, String username){
        this.viewHandler = viewHandler;
        this.userViewModel = userViewModel;
        Bindings.bindContentBidirectional(userViewModel.setRightArea(), rightArea.getChildren());
        Bindings.bindContentBidirectional(userViewModel.menuNodes(), menuPane.getChildren());
        userViewModel.initView(username);
    }



}
