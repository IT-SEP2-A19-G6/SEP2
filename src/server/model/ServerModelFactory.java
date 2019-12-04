package server.model;

import server.model.createTicket.CreateTicketServerModelHandler;
import server.model.createTicket.ICreateTicketServerModel;
import server.model.login.ILoginServerModel;
import server.model.login.LoginServerModelHandler;
import server.persistence.DataFactory;

public class ServerModelFactory {
    private DataFactory dataFactory;
    private ILoginServerModel loginServerModel;
    private ICreateTicketServerModel createTicketServerModel;

    public ServerModelFactory(DataFactory dataFactory) {
        this.dataFactory = dataFactory;
    }

    public ILoginServerModel getLoginServerModel(){
        if (loginServerModel == null){
            loginServerModel = new LoginServerModelHandler(dataFactory.getLoginDOA());
        }
        return loginServerModel;
    }

    public ICreateTicketServerModel getCreateTicketServerModel() {
        if(createTicketServerModel == null) {
            createTicketServerModel = new CreateTicketServerModelHandler(dataFactory.getCreateTicketDAO());
        }
        return createTicketServerModel;
    }




}
