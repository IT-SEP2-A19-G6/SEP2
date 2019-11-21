package server.persistence;

import server.persistence.database.DatabaseConnection;
import server.persistence.login.ILoginDAO;
import server.persistence.login.LoginDOA;

public class DataFactory {
    private DatabaseConnection databaseConnection;
    private ILoginDAO loginDAO;

    public DataFactory(){
        databaseConnection = new DatabaseConnection();
    }

    public ILoginDAO getLoginDOA() {
        if (loginDAO == null) {
            loginDAO = new LoginDOA(databaseConnection);
        }
        return loginDAO;
    }


}
