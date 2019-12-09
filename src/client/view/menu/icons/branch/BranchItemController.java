package client.view.menu.icons.branch;

import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class BranchItemController {
    @FXML
    Label label;

    @FXML
    Circle dot;

    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, String branchName){
        this.viewHandler = viewHandler;
        label.setText(branchName);
        dot.setVisible(true);
    }

    public void doAction(ActionEvent actionEvent) {
        //TODO call viewhandler view
    }

    public void showDot(boolean bool){
        dot.setVisible(bool);
    }

}
