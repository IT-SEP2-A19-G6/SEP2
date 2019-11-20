package Server.persistence;

import Server.persistence.database.DatabaseConnection;
import Server.persistence.login.ILoginDAO;
import Server.persistence.login.LoginDOA;

public class DataFactory {

    public DataFactory(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ILoginDAO loginDAO = new LoginDOA(databaseConnection);
    }
}
