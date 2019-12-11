package client.view.mainview.menu.items.plus;

import client.view.ViewHandler;
import client.view.mainview.menu.items.dotcontroller.IButtonController;
import client.view.mainview.menu.items.dotcontroller.IDotController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class PlusItemController implements IDotController {
    @FXML
    Label label;

    @FXML
    Circle dot;

    private ViewHandler viewHandler;
    private IButtonController buttonController;

    public void init(ViewHandler viewHandler, String iconText, IButtonController buttonController){
        this.buttonController = buttonController;
        this.viewHandler = viewHandler;
        label.setText(iconText);
        setVisibility(false);
    }

    public void doAction(ActionEvent actionEvent) {
        buttonController.plusButtonPressed(this);
        viewHandler.loadCreateTicket();
    }


    @Override
    public void setVisibility(boolean bool) {
        dot.setVisible(bool);
    }
}
