package client.view.mainview.menu.items.plus;

import client.view.ViewHandler;
import client.view.mainview.menu.items.dotcontroller.IButtonController;
import client.view.mainview.menu.items.dotcontroller.IDotController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class PlusItemController implements IDotController {
    @FXML
    Label label;

    @FXML
    Circle dot;

    private IButtonController buttonController;

    public void init(String iconText, IButtonController buttonController){
        this.buttonController = buttonController;

        label.setText(iconText);
        setVisibility(false);
    }

    public void doAction() {
        buttonController.plusButtonPressed(this);
        ViewHandler.getInstance().loadCreateTicket();
    }


    @Override
    public void setVisibility(boolean bool) {
        dot.setVisible(bool);
    }
}
