package client.view.menu;

import client.view.ViewHandler;
import client.view.menu.icons.branch.BranchItemController;
import client.view.menu.icons.client.ClientItemController;
import client.view.menu.icons.plus.PlusItemController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import java.io.IOException;

public class MenuViewController {

    @FXML
    AnchorPane menu;
    private ViewHandler viewHandler;

    public MenuViewController(ViewHandler viewHandler){
        this.viewHandler = viewHandler;
    }

    public void addClientIcon(String username){
        menu.getChildren().add(createClientIcon(username));
    }

    public void addBranchIcon(String branchName){
        menu.getChildren().add(createBranchIcon(branchName));
    }

    public AnchorPane getMenu(){
        return menu;
    }

    public void addPlusIcon(String labelText){
        menu.getChildren().add(createPlusIcon(labelText));
    }

    private GridPane createClientIcon(String username) {
        GridPane content = new GridPane();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../menu/icons/client/ClientItemControl.fxml"));
            GridPane icon  =  loader.load();
            ClientItemController controller = loader.getController();
            controller.init(viewHandler, username);
            content = icon;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private GridPane createBranchIcon(String branchName) {
        GridPane content = new GridPane();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../menu/icons/branch/BranchItemControl.fxml"));
            GridPane icon  =  loader.load();
            BranchItemController controller = loader.getController();
            controller.init(viewHandler, branchName);
            content = icon;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private GridPane createPlusIcon(String labelText) {
        GridPane content = new GridPane();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../menu/icons/plus/plusItemControl.fxml"));
            GridPane icon  =  loader.load();
            PlusItemController controller = loader.getController();
            controller.init(viewHandler, labelText);
            content = icon;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

}
