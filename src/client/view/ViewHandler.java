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
    private LoginViewController loginViewController;
    private Scene loginScene;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        this.mainStage = new Stage();
        viewModelFactory.instantiateViewModel(this);
    }

    public void start() {
        openLoginView();
        mainStage.show();
    }

    private void openLoginView(){
        try {
            if (loginScene==null){
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("../views/Login.fxml"));
                Parent root = loader.load();
                LoginViewController view = loader.getController();
                view.init(viewModelFactory.getLoginViewModel());
                loginScene = new Scene(root);
            }
            mainStage.setTitle("Log in");
            mainStage.setScene(loginScene);
            }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openLoginViewSuccessfulAttempt() {
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource(".../view/login/LoginResult.fxml"));
            Parent root = loader.load();
            mainStage.setTitle("Logged in");
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
