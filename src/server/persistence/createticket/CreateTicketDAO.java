package server.persistence.createticket;

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
    public String addTicket(Ticket createTicket) throws DataConnectionException {

        try {
            String sql = "INSERT INTO " + "Ticket" + " (user_id, subject, description, category, location, ticket_Status)" + " VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = databaseConnection.createPreparedStatement(sql);

            preparedStatement.setInt(1, 1); //TODO delete when real ID works
           //preparedStatement.setInt(1, createTicket.getUserId());
            preparedStatement.setString(2, createTicket.getSubject());
            preparedStatement.setString(3, createTicket.getDescription());
            preparedStatement.setString(4, createTicket.getCategory());
            preparedStatement.setString(5, createTicket.getLocation());
            preparedStatement.setString(6, createTicket.getTicketStatus());
            databaseConnection.executeUpdate(preparedStatement);
            return "OK";
        } catch (SQLException e) {
            System.out.println("Creating ticket failed, no ID obtained" + e.getMessage());
        }
        return "NOT OK";
    }
}
