package server.persistence.createTicket;

import server.exceptions.DataConnectionException;
import server.persistence.database.IDatabaseConnection;
import shared.Ticket;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTicketDAO implements ICreateTicketDAO {

    private IDatabaseConnection databaseConnection;

    public CreateTicketDAO(IDatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void addTicket(Ticket createTicket) throws DataConnectionException {
        String sql = "INSERT INTO " + "Ticket" + " (user_id, subject, description, category, location, ticket_Status)" + " VALUES (?, ?, ?, ?, ?, ?);";

        PreparedStatement preparedStatement = databaseConnection.createPreparedStatement(sql);

        try {
            preparedStatement.setInt(1, 1);
            //preparedStatement.setInt(1, createTicket.getUser().getUser_id());
            preparedStatement.setString(2, createTicket.getSubject());
            preparedStatement.setString(3, createTicket.getDescription());
            preparedStatement.setString(4, createTicket.getCategory());
            preparedStatement.setString(5, createTicket.getLocation());
            preparedStatement.setString(6, createTicket.getTicketStatus());

            databaseConnection.executeUpdate(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
