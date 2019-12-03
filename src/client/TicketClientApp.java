package client;

import client.model.ModelFactory;
import client.network.ClientFactory;
import client.view.ViewHandler;
import client.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class TicketClientApp extends Application {
    private ClientFactory clientFactory;

    @Override
    public void start(Stage stage) {
        clientFactory = new ClientFactory();
        ModelFactory modelFactory = new ModelFactory(clientFactory);
        ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
        ViewHandler viewHandler = new ViewHandler(stage, viewModelFactory);
        viewHandler.start();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        clientFactory.closeConnection();
    }
}
