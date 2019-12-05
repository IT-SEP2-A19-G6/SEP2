package server.persistence.user;

import server.exceptions.DataConnectionException;
import server.persistence.database.IDatabaseConnection;
import shared.Ticket;
import shared.clients.User;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class UserDAO implements IUserDAO {
    private IDatabaseConnection databaseConnection;

    public UserDAO(IDatabaseConnection databaseConnection) {
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

        if (objects.size() > 0){
            for (Object[] obj : objects) {
                Date dateCreated = (Date) obj[0];
                String id = obj[1].toString();
                String userId = obj[2].toString();
                String subject = obj[3].toString();
                String description = obj[4].toString();
                String category = obj[5].toString();
                String location = obj[6].toString();
                String status = obj[7].toString();

                LocalDate createdDate = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(dateCreated) );

                tickets.add(new Ticket(subject, description, location));
            }
        }
        return tickets;
    }

}
