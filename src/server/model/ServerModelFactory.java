package server.model;

import server.model.login.ILoginServerModel;
import server.model.login.LoginServerModelHandler;
import server.model.signup.ISignUpServerModel;
import server.model.signup.SignUpServerModelHandler;
import server.model.ticket.ICreateTicketServerModel;
import server.model.ticket.CreateTicketServerModelHandler;
import server.model.user.IUserServerModel;
import server.model.user.UserServerModelHandler;
import server.persistence.DataFactory;

public class ServerModelFactory {
    private DataFactory dataFactory;
    private ILoginServerModel loginServerModel;
    private ICreateTicketServerModel ticketServerModel;
    private IUserServerModel userServerModel;
    private ISignUpServerModel signUpServerModel;

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

    public IUserServerModel getUserServerModel(){
        if(userServerModel == null){
            userServerModel = new UserServerModelHandler(dataFactory.getUserDAO());
        }
        return userServerModel;
    }


    public ISignUpServerModel getSignUpServerModel() {
        if (signUpServerModel == null){
            signUpServerModel = new SignUpServerModelHandler(dataFactory.getSignUpDAO());
        }
        return signUpServerModel;
    }




}
