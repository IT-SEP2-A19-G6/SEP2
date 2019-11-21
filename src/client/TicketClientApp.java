package client;

import client.model.ModelFactory;
import client.network.ClientSocket;
import client.view.ViewHandler;
import client.viewModel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class TicketClientApp extends Application {


    @Override
    public void start(Stage stage) {
        ModelFactory modelFactory = new ModelFactory();
        ClientSocket clientSocket = new ClientSocket(modelFactory);
        ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start(stage);
        clientSocket.start();
    }
}
