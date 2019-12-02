package server.model;

import server.model.login.ILoginServerModel;
import server.model.login.LoginServerModelHandler;
import server.model.ticket.ICreateTicketServerModel;
import server.model.ticket.CreateTicketServerModelHandler;
import server.persistence.DataFactory;

public class ServerModelFactory {
    private DataFactory dataFactory;
    private ILoginServerModel loginServerModel;
    private ICreateTicketServerModel ticketServerModel;

    public ServerModelFactory(DataFactory dataFactory) {
        this.dataFactory = dataFactory;
    }

    public ILoginServerModel getLoginServerModel(){
        if (loginServerModel == null){
            loginServerModel = new LoginServerModelHandler(dataFactory.getLoginDOA());
        }
        return loginServerModel;
    }

    public ICreateTicketServerModel getTicketServerModel() {
        if(ticketServerModel == null){
            ticketServerModel = new CreateTicketServerModelHandler();//TODO method from posgres
        }
        return ticketServerModel;
    }




}
