package server.persistence.createTicket;

import server.exceptions.DataConnectionException;
import server.persistence.database.DatabaseConnection;
import shared.CreateTicket;

public class testRun {

    public static void main(String[] args) {

        CreateTicketDAO ctdao = new CreateTicketDAO(new DatabaseConnection());
        try {
            ctdao.writeToTable(new CreateTicket("1","testsub", "testDesc", "testCat", "testloc", "OPEN"));
        } catch (DataConnectionException e) {
            e.printStackTrace();
        }
    }
}
