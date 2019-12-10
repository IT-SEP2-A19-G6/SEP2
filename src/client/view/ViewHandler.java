package client.view;

import client.view.mainview.MainViewController;
import client.view.createticket.CreateTicketViewController;
import client.view.login.LoginViewController;
import client.view.mainview.menu.MenuViewController;
import client.view.signup.SignUpViewController;
import client.view.ticketlist.TicketListController;
import client.viewmodel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import shared.TicketListExchange;

import java.io.IOException;

public class ViewHandler {
    private Stage stage;
    private ViewModelFactory viewModelFactory;
    private Scene loginScene;
    private Scene createTicketScene;
    private Scene signUpScene;
    private Scene mainScene;
    private VBox menu;
    private Pane createTicketPane;
    private AnchorPane ticketListPane;
    private MainViewController mainViewController;
    private TicketListController ticketListController;

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

    public void loadCreateTicket() {
        if (createTicketPane == null) {
            createTicketPane = new Pane();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("createticket/CreateTicketView.fxml"));
                createTicketPane = loader.load();
                CreateTicketViewController controller = loader.getController();
                controller.init(viewModelFactory.getCreateTicketViewModel());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mainViewController.setRightArea(createTicketPane);
    }

    public void loadTicketList(TicketListExchange ticketListExchange) {
        if (ticketListPane == null) {
            ticketListPane = new AnchorPane();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ticketlist/TicketListView.fxml"));
                ticketListPane = loader.load();
                ticketListController = loader.getController();
                ticketListController.init(viewModelFactory.getTicketListViewModel());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ticketListController.requestList(ticketListExchange);
        setMainRightArea(ticketListPane);
    }


    public void loadMenu() {
        if (menu == null){
            menu = new VBox();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("mainview/menu/MenuView.fxml"));
                menu = loader.load();
                MenuViewController controller = loader.getController();
                controller.init(this, viewModelFactory.getMenuViewModel());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mainViewController.setMenu(menu);
    }

    public void openMainView() {
        FXMLLoader loader = new FXMLLoader();
        if (createTicketScene == null) {
            Parent root = getRootByPath("mainview/MainView.fxml", loader);
            mainViewController = loader.getController();
            mainViewController.init(this);
            mainScene = new Scene(root);
        }
        stage.setTitle("Main View");
        stage.setScene(mainScene);
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

    public void setMainRightArea(Node node){
        mainViewController.setRightArea(node);
    }

}
