package server.persistence;

import server.persistence.createTicket.CreateTicketDAO;
import server.persistence.createTicket.ICreateTicketDAO;
import server.persistence.database.DatabaseConnection;
import server.persistence.database.IDatabaseConnection;
import server.persistence.login.ILoginDAO;
import server.persistence.login.LoginDAO;
import server.persistence.signup.ISignUpDAO;
import server.persistence.signup.SignUpDAO;
import server.persistence.ticketlist.ITicketListDAO;
import server.persistence.ticketlist.TicketListDAO;

public class DataFactory {
    private IDatabaseConnection databaseConnection;
    private ILoginDAO loginDAO;
    private ICreateTicketDAO createTicketDAO;
    private ITicketListDAO userDAO;
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

    public ICreateTicketDAO getCreateTicketDAO() {
        if(createTicketDAO == null) {
            createTicketDAO = new CreateTicketDAO(databaseConnection);
        }
        return createTicketDAO;
    }

    public ITicketListDAO getUserDAO(){
        if (userDAO == null){
            userDAO = new TicketListDAO(databaseConnection);
        }
        return userDAO;
    }

    public ISignUpDAO getSignUpDAO(){
        if (signUpDAO == null){
            signUpDAO = new SignUpDAO(databaseConnection);
        }
        return signUpDAO;
    }


}