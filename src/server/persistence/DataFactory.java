package server.persistence;

import server.persistence.database.DatabaseConnection;
import server.persistence.login.ILoginDAO;
import server.persistence.login.LoginDOA;

public class DataFactory {

    public DataFactory(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ILoginDAO loginDAO = new LoginDOA(databaseConnection);
    }
}
