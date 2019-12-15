package server.model;

import server.model.communication.ITicketReplyServerModel;
import server.model.communication.TicketReplyServerModelHandler;
import server.model.createticket.CreateTicketServerModelHandler;
import server.model.createticket.ICreateTicketServerModel;
import server.model.login.ILoginServerModel;
import server.model.login.LoginServerModelHandler;
import server.model.signup.ISignUpServerModel;
import server.model.signup.SignUpServerModelHandler;
import server.model.ticketlist.ITicketListServerModel;
import server.model.ticketlist.TicketListServerModelHandler;
import server.model.updateticket.IUpdateTicketServerModel;
import server.model.updateticket.UpdateTicketServerModel;
import server.persistence.DataFactory;

public class ServerModelFactory {
    private DataFactory dataFactory;
    private ILoginServerModel loginServerModel;
    private ICreateTicketServerModel ticketServerModel;
    private ITicketListServerModel ticketListServerModel;
    private ISignUpServerModel signUpServerModel;
    private ITicketReplyServerModel ticketReplyServerModel;
    private IUpdateTicketServerModel updateTicketServerModel;

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
            ticketServerModel = new CreateTicketServerModelHandler(dataFactory.getCreateTicketDAO());
        }
        return ticketServerModel;
    }

    public ITicketListServerModel getTicketListServerModel(){
        if(ticketListServerModel == null){
            ticketListServerModel = new TicketListServerModelHandler(dataFactory.getTicketListDAO());
        }
        return ticketListServerModel;
    }

    public ISignUpServerModel getSignUpServerModel() {
        if (signUpServerModel == null){
            signUpServerModel = new SignUpServerModelHandler(dataFactory.getSignUpDAO());
        }
        return signUpServerModel;
    }

    public ITicketReplyServerModel getTicketReplyServerModel(){
        if (ticketReplyServerModel == null){
            ticketReplyServerModel = new TicketReplyServerModelHandler(dataFactory.getTicketReplyDAO());
        }
        return ticketReplyServerModel;
    }

    public IUpdateTicketServerModel getUpdateTicketServerModel() {
        if (updateTicketServerModel == null){
            updateTicketServerModel = new UpdateTicketServerModel(dataFactory.getUpdateTicketDAO());
        }
        return updateTicketServerModel;
    }
}
