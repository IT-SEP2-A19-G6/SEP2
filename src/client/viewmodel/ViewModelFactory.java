package client.viewmodel;

import client.model.ModelFactory;
import client.viewmodel.login.LoginViewModel;
import client.viewmodel.createticket.CreateTicketViewModel;
import client.viewmodel.signup.SignUpViewModel;
import client.viewmodel.client.ClientViewModel;

public class ViewModelFactory {
    private ModelFactory modelFactory;
    private LoginViewModel loginViewModel;
    private CreateTicketViewModel createIssueViewModel;
    private ClientViewModel userViewModel;
    private SignUpViewModel signUpViewModel;

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

    public ClientViewModel getUserViewModel(){
        if (userViewModel == null) {
            userViewModel = new ClientViewModel(modelFactory.getUserModel());
        }
        return userViewModel;
    }

    public SignUpViewModel getSignUpViewModel() {
        if (signUpViewModel == null) {
            signUpViewModel = new SignUpViewModel(modelFactory.getSignUpModel());
        }
        return signUpViewModel;
    }
}
