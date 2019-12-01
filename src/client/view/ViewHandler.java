package client.view;

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

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        this.stage = new Stage();
    }

    public void start(Stage stage) {
        this.stage = stage;
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
        stage.setTitle("Login");


        if (root != null) {
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
    }

}
