package server.persistence;

import server.persistence.database.DatabaseConnection;
import server.persistence.database.IDatabaseConnection;
import server.persistence.login.ILoginDAO;
import server.persistence.login.LoginDAO;
import server.persistence.signup.ISignUpDAO;
import server.persistence.signup.SignUpDAO;
import server.persistence.user.IUserDAO;
import server.persistence.user.UserDAO;

public class DataFactory {
    private IDatabaseConnection databaseConnection;
    private ILoginDAO loginDAO;
    private IUserDAO userDAO;
    private ISignUpDAO signUpDAO;

    public DataFactory(){
        databaseConnection = new DatabaseConnection();
    }

    public ILoginDAO getLoginDOA() {
        if (loginDAO == null) {
            loginDAO = new LoginDAO(databaseConnection);
        }
        return loginDAO;
    }

    public IUserDAO getUserDAO(){
        if (userDAO == null){
            userDAO = new UserDAO(databaseConnection);
        }
        return userDAO;
    }

    public ISignUpDAO getSignUpDAO(){
        if (signUpDAO == null) {
            signUpDAO = new SignUpDAO(databaseConnection);
        }
        return signUpDAO;
    }

}
