package client.view.menu.icons.client;

import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class ClientItemController {
    @FXML
    Label label;

    @FXML
    Circle dot;

    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, String username){
        this.viewHandler = viewHandler;
        label.setText(username);
        dot.setVisible(true);
    }

    public void doAction(ActionEvent actionEvent) {
        //TODO call viewhandler view
    }

    public void showDot(boolean bool){
        dot.setVisible(bool);
    }

}
