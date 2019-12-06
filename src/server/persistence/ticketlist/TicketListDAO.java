package server.persistence.ticketlist;

import server.exceptions.DataConnectionException;
import server.persistence.database.IDatabaseConnection;
import shared.Ticket;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class TicketListDAO implements ITicketListDAO {
    private IDatabaseConnection databaseConnection;

    public TicketListDAO(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public ArrayList<Ticket> getClientTickets(String username) throws DataConnectionException {
        String sql = "SELECT * FROM " + databaseConnection.getSchemaName() + "." + databaseConnection.getTicketTableName() + " t " +
        "INNER JOIN Account_Client c ON t.User_Id = c.id " +
        "WHERE c.Username = '" + username + "' " +
        "GROUP BY t.Id_Ticket, c.id;";

        ArrayList<Object[]> objects = null;
        objects = databaseConnection.executePreparedQuery(sql);

        ArrayList<Ticket> tickets = new ArrayList<>();

        Date dateCreated;
        int id;
        int userId;
        String subject;
        String description;
        String category;
        String location;
        String status;


        if (objects.size() > 0){
            for (Object[] obj : objects) {

                dateCreated = (Date) obj[0];
                id = (int) obj[1];
                userId = (int) obj[2];
                if (obj[3] == null){
                    subject = "";
                } else {
                    subject = obj[3].toString();
                }
                if (obj[4] == null){
                    description = "";
                } else {
                    description = obj[4].toString();
                }
                if (obj[5] == null){
                    category = "";
                } else {
                    category = obj[5].toString();
                }
                if (obj[6] == null){
                    location = "";
                } else {
                    location = obj[6].toString();
                }
                if (obj[7] == null){
                    status = "";
                } else {
                    status = obj[7].toString();
                }

                LocalDate createdDate = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(dateCreated) );  //TODO set correct pattern

                tickets.add(new Ticket(subject, description, location));
            }
        }
        return tickets;
    }

}
