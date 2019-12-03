package client.view.user;

import client.view.ViewHandler;
import client.viewmodel.user.UserViewModel;

import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;


public class UserViewController {


    @FXML
    Label username;

    @FXML
    ScrollPane ticketScroll;

    @FXML
    VBox ticketBox;

    @FXML
    Button newTicket;



    private ViewHandler viewHandler;
    private UserViewModel userViewModel;



    public void init(ViewHandler viewHandler, UserViewModel userViewModel, String username){
        this.viewHandler = viewHandler;
        this.userViewModel = userViewModel;
        this.username.textProperty().bindBidirectional(userViewModel.userLabelProperty());
        userViewModel.initView(username);
        updateTickets();
    }

    private void updateTickets(){
        ticketBox.getChildren().addAll(userViewModel.ticketNodes());
    }


    public void addNewTicket(ActionEvent actionEvent) {
        //TODO ADD NEW TICKET HERE
        userViewModel.addTicket();
    }


}
