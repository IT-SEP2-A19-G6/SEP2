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
    private Scene createIssueScene;

    public ViewHandler(Stage stage, ViewModelFactory viewModelFactory) {
        this.stage = stage;
        this.viewModelFactory = viewModelFactory;
        this.stage = new Stage();
    }

    public void start() {
        openCreateIssueView();
        stage.show();
    }


    private void openLoginView(){

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

    private void openCreateIssueView() {
        FXMLLoader loader = new FXMLLoader();

        if (createIssueScene == null) {
            Parent root = getRootByPath("createticket/CreateTicketView.fxml", loader);
            CreateTicketViewController controller = loader.getController();
            controller.init(viewModelFactory.getCreateIssueViewModel());
            createIssueScene = new Scene(root);
        }
        stage.setTitle("Create Ticket");
        stage.setScene(createIssueScene);
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
