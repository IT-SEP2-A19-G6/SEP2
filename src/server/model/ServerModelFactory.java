package server.model;


import server.model.login.ILoginServerModel;
import server.model.login.LoginServerModelHandler;
import server.model.user.IUserServerModel;
import server.model.user.UserServerModelHandler;
import server.persistence.DataFactory;

public class ServerModelFactory {
    private DataFactory dataFactory;
    private ILoginServerModel loginServerModel;
    private IUserServerModel userModel;

    public ServerModelFactory(DataFactory dataFactory) {
        this.dataFactory = dataFactory;
    }

    public ILoginServerModel getLoginServerModel(){
        if (loginServerModel == null){
            loginServerModel = new LoginServerModelHandler(dataFactory.getLoginDOA());
        }
        return loginServerModel;
    }

    public IUserServerModel getUserServerModel(){
        if(userModel == null){
            userModel = new UserServerModelHandler(dataFactory.getUserDAO());
        }
        return userModel;
    }




}
