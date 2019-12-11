package client.viewmodel;

import client.model.ModelFactory;
import client.viewmodel.communication.TicketReplyViewModel;
import client.viewmodel.createticket.CreateTicketViewModel;
import client.viewmodel.login.LoginViewModel;
import client.viewmodel.mainview.MenuViewModel;
import client.viewmodel.signup.SignUpViewModel;
import client.viewmodel.statemachine.StateHandler;
import client.viewmodel.ticketlist.TicketListViewModel;

public class ViewModelFactory {
    private ModelFactory modelFactory;
    private LoginViewModel loginViewModel;
    private CreateTicketViewModel createIssueViewModel;
    private SignUpViewModel signUpViewModel;
    private TicketListViewModel ticketListViewModel;
    private MenuViewModel menuViewModel;
    private TicketReplyViewModel ticketReplyViewModel;

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
        new StateHandler(this, modelFactory.getLoginModel());
    }

    public LoginViewModel getLoginViewModel() {
        if(loginViewModel == null) {
            loginViewModel = new LoginViewModel(modelFactory.getLoginModel());
        }
        return loginViewModel;
    }

    public MenuViewModel getMenuViewModel() {
        if (menuViewModel == null){
            menuViewModel = new MenuViewModel();
        }
        return menuViewModel;
    }

    public TicketListViewModel getTicketListViewModel() {
        if (ticketListViewModel == null) {
            ticketListViewModel = new TicketListViewModel(modelFactory.getTicketListModel());
        }
        return ticketListViewModel;
    }

    public CreateTicketViewModel getCreateTicketViewModel() {
        if(createIssueViewModel == null) {
            createIssueViewModel = new CreateTicketViewModel(modelFactory.getCreateTicketModel());
        }
        return createIssueViewModel;
    }

    public SignUpViewModel getSignUpViewModel() {
        if (signUpViewModel == null) {
            signUpViewModel = new SignUpViewModel(modelFactory.getSignUpModel());
        }
        return signUpViewModel;
    }

    public TicketReplyViewModel getTicketReplyViewModel() {
        if (ticketReplyViewModel == null) {
            ticketReplyViewModel = new TicketReplyViewModel(modelFactory.ticketReplyModel());
        }
        return ticketReplyViewModel;
    }
}
