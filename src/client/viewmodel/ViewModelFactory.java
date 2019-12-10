package client.viewmodel;

import client.model.ModelFactory;
import client.viewmodel.client.ClientViewModel;
import client.viewmodel.createticket.CreateTicketViewModel;
import client.viewmodel.login.LoginViewModel;
import client.viewmodel.menu.MenuViewModel;
import client.viewmodel.signup.SignUpViewModel;
import client.viewmodel.ticketlist.TicketListViewModel;

public class ViewModelFactory {
    private ModelFactory modelFactory;
    private LoginViewModel loginViewModel;
    private CreateTicketViewModel createIssueViewModel;
    private ClientViewModel clientViewModel;
    private SignUpViewModel signUpViewModel;
    private TicketListViewModel ticketListViewModel;
    private MenuViewModel menuViewModel;

    public ViewModelFactory(ModelFactory mf) {
        this.modelFactory = mf;
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

    public ClientViewModel getClientViewModel(){
        if (clientViewModel == null) {
            clientViewModel = new ClientViewModel(getMenuViewModel(), getTicketListViewModel());
        }
        return clientViewModel;
    }

    public SignUpViewModel getSignUpViewModel() {
        if (signUpViewModel == null) {
            signUpViewModel = new SignUpViewModel(modelFactory.getSignUpModel());
        }
        return signUpViewModel;
    }
}
