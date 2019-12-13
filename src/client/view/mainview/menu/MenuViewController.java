package client.view.mainview.menu;

import client.view.ViewHandler;
import client.view.mainview.menu.items.branch.BranchItemController;
import client.view.mainview.menu.items.client.ClientItemController;
import client.view.mainview.menu.items.dotcontroller.DotHandler;
import client.view.mainview.menu.items.dotcontroller.IButtonController;
import client.view.mainview.menu.items.dotcontroller.IDotController;
import client.view.mainview.menu.items.plus.PlusItemController;
import client.viewmodel.mainview.MenuViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class MenuViewController {

    @FXML
    VBox menu;
    private BooleanProperty clientIcon;
    private BooleanProperty isUser;
    private StringProperty clientLabel;
    private BooleanProperty plusIcon;
    private StringProperty plusLabel;
    private BooleanProperty branchIcon;
    private StringProperty branchLabel;
    private ViewHandler viewHandler;
    private IButtonController buttonController;

    public void init(ViewHandler viewHandler, MenuViewModel menuViewModel){
        this.viewHandler = viewHandler;
        clientIcon = new SimpleBooleanProperty();
        isUser = new SimpleBooleanProperty();
        clientLabel = new SimpleStringProperty();
        plusIcon = new SimpleBooleanProperty();
        plusLabel = new SimpleStringProperty();
        branchIcon = new SimpleBooleanProperty();
        branchLabel = new SimpleStringProperty();
        clientIcon.bindBidirectional(menuViewModel.showClientIconProperty());
        isUser.bindBidirectional(menuViewModel.isUserProperty());
        clientLabel.bindBidirectional(menuViewModel.clientLabelProperty());
        plusIcon.bindBidirectional(menuViewModel.showPlusIconProperty());
        plusLabel.bindBidirectional(menuViewModel.plusLabelProperty());
        branchIcon.bindBidirectional(menuViewModel.showBranchIconProperty());
        branchLabel.bindBidirectional(menuViewModel.branchLabelProperty());
        buttonController = new DotHandler();
        buildMenu();
    }

    private void buildMenu() {
        if (clientIcon.getValue()){
            menu.getChildren().add(createClientIcon(clientLabel.getValue(), isUser.getValue()));
        }
        if (plusIcon.getValue()){
            menu.getChildren().add(createPlusIcon(plusLabel.getValue()));
        }
        if (branchIcon.getValue()){
            menu.getChildren().add(createBranchIcon(branchLabel.getValue()));
        }
    }

    private GridPane createClientIcon(String username, boolean isUser) {
        GridPane content = new GridPane();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("items/client/ClientItemControl.fxml"));
            GridPane icon  =  loader.load();
            ClientItemController controller = loader.getController();
            controller.init(viewHandler, username, isUser, buttonController);
            content = icon;
            buttonController.addDotController(controller);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private GridPane createBranchIcon(String branchName) {
        GridPane content = new GridPane();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("items/branch/BranchItemControl.fxml"));
            GridPane icon  =  loader.load();
            BranchItemController controller = loader.getController();
            controller.init(viewHandler, branchName, buttonController);
            content = icon;
            buttonController.addDotController(controller);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private GridPane createPlusIcon(String labelText) {
        GridPane content = new GridPane();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("items/plus/plusItemControl.fxml"));
            GridPane icon  =  loader.load();
            PlusItemController controller = loader.getController();
            controller.init(viewHandler, labelText, buttonController);
            content = icon;
            buttonController.addDotController(controller);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

}
