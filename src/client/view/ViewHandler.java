package client.view;

import client.view.login.LoginViewController;
import client.viewModel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {
    private Stage mainStage;
    private ViewModelFactory viewModelFactory;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        this.mainStage = new Stage();
    }

    public void start(Stage stage) {
        mainStage = stage;
        openLoginView();
    }

    private void openLoginView(){
        Scene scene;
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;

        loader.setLocation(getClass().getResource("login/loginView.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LoginViewController view = loader.getController();
        view.init(this, viewModelFactory.getLoginViewModel());
        mainStage.setTitle("Login");


        scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }

}
