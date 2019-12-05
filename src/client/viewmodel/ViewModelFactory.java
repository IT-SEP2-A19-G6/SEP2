package client.viewModel;

import client.model.ModelFactory;
import client.viewModel.Login.LoginViewModel;
import client.viewModel.createticket.CreateTicketViewModel;

public class ViewModelFactory {
    private ModelFactory modelFactory;
    private LoginViewModel loginViewModel;
    private CreateTicketViewModel createIssueViewModel;

    public ViewModelFactory(ModelFactory mf) {
        this.modelFactory = mf;
    }

    public LoginViewModel getLoginViewModel() {
        if(loginViewModel == null) {
            loginViewModel = new LoginViewModel(modelFactory.getLoginModel());
        }
        return loginViewModel;
    }


    public CreateTicketViewModel getCreateTicketViewModel() {
        if(createIssueViewModel == null) {
            createIssueViewModel = new CreateTicketViewModel(modelFactory.getCreateTicketModel());
        }
        return createIssueViewModel;
    }
}
