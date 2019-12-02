package server.model;

import server.model.login.ILoginServerModel;
import server.model.login.LoginServerModelHandler;
import server.model.ticket.ITicketServerModel;
import server.model.ticket.TicketServerModelHandler;
import server.persistence.DataFactory;

public class ServerModelFactory {
    private DataFactory dataFactory;
    private ILoginServerModel loginServerModel;
    private ITicketServerModel ticketServerModel;

    public ServerModelFactory(DataFactory dataFactory) {
        this.dataFactory = dataFactory;
    }

    public ILoginServerModel getLoginServerModel(){
        if (loginServerModel == null){
            loginServerModel = new LoginServerModelHandler(dataFactory.getLoginDOA());
        }
        return loginServerModel;
    }

    public ITicketServerModel getTicketServerModel() {
        if(ticketServerModel == null){
            ticketServerModel = new TicketServerModelHandler();//TODO method from posgres
        }
        return ticketServerModel;
    }




}
