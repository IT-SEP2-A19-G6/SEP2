package server.persistence.createTicket;

import server.exceptions.DataConnectionException;
import server.persistence.database.IDatabaseConnection;
import shared.CreateTicket;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTicketDAO implements ICreateTicketDAO {

    private IDatabaseConnection databaseConnection;

    public CreateTicketDAO(IDatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void addTicket(CreateTicket createTicket) throws DataConnectionException {
        String sql = "INSERT INTO " + "Ticket" + " VALUES (?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement preparedStatement = databaseConnection.createPreparedStatement(sql);

        try {
            preparedStatement.setInt(1, 2);
            preparedStatement.setInt(2, 1);
            preparedStatement.setString(3, createTicket.getSubject());
            preparedStatement.setString(4, createTicket.getDescription());
            preparedStatement.setString(5, createTicket.getCategory());
            preparedStatement.setString(6, createTicket.getLocation());
            preparedStatement.setString(7, createTicket.getTicketStatus());

            databaseConnection.executeUpdate(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
