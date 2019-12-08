package client.model;

import client.model.createticket.CreateTicketModelHandler;
import client.model.createticket.ICreateTicketModel;
import client.model.login.ILoginModel;
import client.model.login.LoginModelHandler;
import client.model.signup.ISignUpModel;
import client.model.signup.SignUpModelHandler;
import client.model.ticketlist.ITicketListModel;
import client.model.ticketlist.TicketListModelHandler;
import client.network.ClientFactory;

public class ModelFactory {
    private ILoginModel loginModel;
    private ICreateTicketModel createTicketModel;
    private ClientFactory clientFactory;
    private ITicketListModel ticketListModel;
    private ISignUpModel signUpModel;


    public ModelFactory(ClientFactory clientFactory){
        this.clientFactory = clientFactory;
    }

    public ILoginModel getLoginModel() {
        if (loginModel == null) {
            loginModel = new LoginModelHandler(clientFactory.getLoginClient());
        }
        return loginModel;
    }


    public ICreateTicketModel getCreateTicketModel() {
        if (createTicketModel == null) {
            createTicketModel = new CreateTicketModelHandler(clientFactory.getCreateTicketClient());
        }
        return createTicketModel;
    }

    public ITicketListModel getTicketListModel(){
        if (ticketListModel == null) {
            ticketListModel = new TicketListModelHandler(clientFactory.getTicketListClient());
        }
        return ticketListModel;
    }

    public ISignUpModel getSignUpModel() {
        if (signUpModel == null){
            signUpModel = new SignUpModelHandler(clientFactory.getSignUpClient());
        }
        return signUpModel;
    }
}
