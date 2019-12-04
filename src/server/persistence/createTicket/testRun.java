package server.persistence.createTicket;

import server.exceptions.DataConnectionException;
import server.persistence.database.DatabaseConnection;
import shared.CreateTicket;
import shared.clients.User;

public class testRun {

    public static void main(String[] args) {

        CreateTicketDAO ctdao = new CreateTicketDAO(new DatabaseConnection());
        try {
            ctdao.addTicket(new CreateTicket(new User("John Snow", "DragonLady69"),"testsub", "testDesc", "testCat", "testloc", "OPEN"));
        } catch (DataConnectionException e) {
            e.printStackTrace();
        }
    }
}
