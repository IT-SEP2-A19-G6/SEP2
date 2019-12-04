package server.persistence;

import server.persistence.createTicket.CreateTicketDAO;
import server.persistence.createTicket.ICreateTicketDAO;
import server.persistence.database.DatabaseConnection;
import server.persistence.database.IDatabaseConnection;
import server.persistence.login.ILoginDAO;
import server.persistence.login.LoginDAO;

public class DataFactory {
    private IDatabaseConnection databaseConnection;
    private ILoginDAO loginDAO;
    private ICreateTicketDAO createTicketDAO;

    public DataFactory(){
        databaseConnection = new DatabaseConnection();
    }

    public ILoginDAO getLoginDOA() {
        if (loginDAO == null) {
            loginDAO = new LoginDAO(databaseConnection);
        }
        return loginDAO;
    }

    public ICreateTicketDAO getCreateTicketDAO() {
        if(createTicketDAO == null) {
            createTicketDAO = new CreateTicketDAO(databaseConnection);
        }
        return createTicketDAO;
    }


}