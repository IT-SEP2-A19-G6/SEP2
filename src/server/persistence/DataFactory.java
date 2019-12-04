package server.persistence;

import server.persistence.database.DatabaseConnection;
import server.persistence.database.IDatabaseConnection;
import server.persistence.login.ILoginDAO;
import server.persistence.login.LoginDOA;

public class DataFactory {
    private IDatabaseConnection databaseConnection;
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
