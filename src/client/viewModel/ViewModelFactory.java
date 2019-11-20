package client.viewModel;

import client.model.ModelFactory;
import client.view.ViewHandler;
import client.viewModel.login.LoginViewModel;

public class ViewModelFactory {
    private ModelFactory modelFactory;
    private LoginViewModel loginViewModel;
    private ViewHandler viewHandler;

    public ViewModelFactory(ModelFactory mf) {
        this.modelFactory = mf;
    }

    public void instantiateViewModel(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        loginViewModel = new LoginViewModel(modelFactory.getLoginModel(), viewHandler);
    }
    public LoginViewModel getLoginViewModel() {
        if(loginViewModel == null) {
            loginViewModel = new LoginViewModel(modelFactory.getLoginModel(), viewHandler);
        }
        return loginViewModel;
    }
}
