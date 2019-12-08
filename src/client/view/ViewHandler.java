package client.view;

import client.view.client.ClientViewController;
import client.view.createticket.CreateTicketViewController;
import client.view.login.LoginViewController;
import client.view.signup.SignUpViewController;
import client.viewmodel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {
    private Stage stage;
    private ViewModelFactory viewModelFactory;

    private Scene loginScene;
    private Scene createTicketScene;
    private Scene signUpScene;
    private Scene clientScene;

    public ViewHandler(Stage stage, ViewModelFactory viewModelFactory) {
        this.stage = stage;
        this.viewModelFactory = viewModelFactory;
    }

    public void start() {
        openLoginView();
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

    // TODO: This could be one way to load the anchorpane into the current view.
    // if we go with this, we need to figure out how to do class diagram
    public AnchorPane loadCreateTicketView() {
        AnchorPane content = new AnchorPane();
        try {
            FXMLLoader loader = new FXMLLoader();
            content = loader.load(getClass().getResource("createticket/CreateTicketView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
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

    public void openClientView() {
        FXMLLoader loader = new FXMLLoader();

        if (createTicketScene == null) {
            Parent root = getRootByPath("client/ClientView.fxml", loader);
            ClientViewController controller = loader.getController();
            controller.init(this, viewModelFactory.getClientViewModel());
            clientScene = new Scene(root);
        }
        stage.setTitle("Main View");
        stage.setScene(clientScene);
    }

    public void openSignUpView() {
        FXMLLoader loader = new FXMLLoader();

        if (createTicketScene == null) {
            Parent root = getRootByPath("signup/SignUpView.fxml", loader);
            SignUpViewController controller = loader.getController();
            controller.init(this, viewModelFactory.getSignUpViewModel());
            signUpScene = new Scene(root);
        }
        stage.setTitle("Sign Up");
        stage.setScene(signUpScene);
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
