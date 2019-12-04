package client.view;

import client.view.createticket.CreateTicketViewController;
import client.view.login.LoginViewController;
import client.viewModel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {
    private Stage stage;
    private ViewModelFactory viewModelFactory;

    private Scene loginScene;
    private Scene createTicketScene;

    public ViewHandler(Stage stage, ViewModelFactory viewModelFactory) {
        this.stage = stage;
        this.viewModelFactory = viewModelFactory;
    }

    public void start() {
        openCreateTicketView();
        stage.show();
    }


    public void openLoginView(){

        FXMLLoader loader = new FXMLLoader();

        if (loginScene == null) {
            Parent root = getRootByPath("login/LoginView.fxml", loader);
            LoginViewController controller = loader.getController();
            controller.init(this, viewModelFactory.getLoginViewModel());
            loginScene = new Scene(root);
        }
        stage.setTitle("Login");
        stage.setScene(loginScene);


    }

    public void openCreateTicketView() {
        FXMLLoader loader = new FXMLLoader();

        if (createTicketScene == null) {
            Parent root = getRootByPath("createticket/CreateTicketView.fxml", loader);
            CreateTicketViewController controller = loader.getController();
            controller.init(viewModelFactory.getCreateTicketViewModel());
            createTicketScene = new Scene(root);
        }
        stage.setTitle("Create Ticket");
        stage.setScene(createTicketScene);
    }

    public Stage getStage(){
        return stage;
    }

    private Parent getRootByPath(String path, FXMLLoader loader) {
        loader.setLocation(getClass().getResource(path));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

}
