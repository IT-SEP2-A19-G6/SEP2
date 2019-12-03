package client.viewmodel;

import client.model.ModelFactory;
import client.viewmodel.login.LoginViewModel;
import client.viewmodel.user.UserViewModel;

public class ViewModelFactory {
    private ModelFactory modelFactory;
    private LoginViewModel loginViewModel;
    private UserViewModel userViewModel;

    public ViewModelFactory(ModelFactory mf) {
        this.modelFactory = mf;
    }

    public LoginViewModel getLoginViewModel() {
        if(loginViewModel == null) {
            loginViewModel = new LoginViewModel(modelFactory.getLoginModel());
        }
        return loginViewModel;
    }

    public UserViewModel getUserViewModel(){
        if (userViewModel == null) {
            userViewModel = new UserViewModel(modelFactory.getUserModel());
        }
        return userViewModel;
    }
}
