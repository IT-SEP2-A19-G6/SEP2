package client;

import client.model.ModelFactory;
import client.network.ClientSocket;
import client.view.ViewHandler;
import client.viewModel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class TicketClientApp extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        ModelFactory modelFactory = new ModelFactory();
        ClientSocket clientSocket = new ClientSocket(modelFactory);
        ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
        //TODO shouldn't the viewHandler take a stage also??
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start();
        clientSocket.start();

    }
}
