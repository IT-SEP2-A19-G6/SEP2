package client.viewModel;

import client.model.ModelFactory;
import client.view.ViewHandler;
import client.viewModel.login.LoginViewModel;

public class ViewModelFactory {
    private ModelFactory modelFactory;
    private LoginViewModel loginViewModel;

    public ViewModelFactory(ModelFactory mf) {
        this.modelFactory = mf;
    }

    public LoginViewModel getLoginViewModel() {
        if(loginViewModel == null) {
            loginViewModel = new LoginViewModel(modelFactory.getLoginModel());
        }
        return loginViewModel;
    }
}
