package server.persistence;

import server.persistence.admin.AdminDAO;
import server.persistence.admin.IAdminDAO;
import server.persistence.communication.ITicketReplyDAO;
import server.persistence.communication.TicketReplyDAO;
import server.persistence.createticket.CreateTicketDAO;
import server.persistence.createticket.ICreateTicketDAO;
import server.persistence.database.DatabaseConnection;
import server.persistence.database.IDatabaseConnection;
import server.persistence.login.ILoginDAO;
import server.persistence.login.LoginDAO;
import server.persistence.signup.ISignUpDAO;
import server.persistence.signup.SignUpDAO;
import server.persistence.ticketlist.ITicketListDAO;
import server.persistence.ticketlist.TicketListDAO;
import server.persistence.updateticket.IUpdateTicketDAO;
import server.persistence.updateticket.UpdateTicketDAO;

public class DataFactory {
    private IDatabaseConnection databaseConnection;
    private ILoginDAO loginDAO;
    private ICreateTicketDAO createTicketDAO;
    private ITicketListDAO ticketListDAO;
    private ISignUpDAO signUpDAO;
    private ITicketReplyDAO ticketReplyDAO;
    private IUpdateTicketDAO updateTicketDAO;
    private IAdminDAO adminDAO;

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

    public ITicketListDAO getTicketListDAO(){
        if (ticketListDAO == null){
            ticketListDAO = new TicketListDAO(databaseConnection);
        }
        return ticketListDAO;
    }

    public ISignUpDAO getSignUpDAO(){
        if (signUpDAO == null){
            signUpDAO = new SignUpDAO(databaseConnection);
        }
        return signUpDAO;
    }

    public ITicketReplyDAO getTicketReplyDAO(){
        if (ticketReplyDAO == null){
            ticketReplyDAO = new TicketReplyDAO(databaseConnection);
        }
        return ticketReplyDAO;
    }


    public IUpdateTicketDAO getUpdateTicketDAO() {
        if (updateTicketDAO == null){
            updateTicketDAO = new UpdateTicketDAO(databaseConnection);
        }
        return updateTicketDAO;
    }

    public IAdminDAO getAdminDAO(){
        if(adminDAO == null){
            adminDAO = new AdminDAO(databaseConnection);
        }
        return adminDAO;
    }
}