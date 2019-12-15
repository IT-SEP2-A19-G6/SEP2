package server.persistence.updateticket;

import server.exceptions.DataConnectionException;
import server.persistence.database.IDatabaseConnection;
import shared.Ticket;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTicketDAO implements IUpdateTicketDAO {

    private IDatabaseConnection databaseConnection;

    public UpdateTicketDAO(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    @Override
    public void updateTicket(Ticket ticket) throws DataConnectionException {
        try {
            String sql = "UPDATE " + databaseConnection.getTicketTableName() +
                    " SET" +
                    " subject = ?," +
                    " description = ?," +
                    " location = ?," +
                    " ticket_status = ?," +
                    " id_branch = (select id_branch from branch where branchName = ?)," +
                    " assignee = (select id from account_client where Username = ?)" +
                    " WHERE ticket.id = ?";

            PreparedStatement preparedStatement = databaseConnection.createPreparedStatement(sql);

            preparedStatement.setString(1, ticket.getSubject());
            preparedStatement.setString(2, ticket.getDescription());
            preparedStatement.setString(3, ticket.getLocation());
            preparedStatement.setString(4, ticket.getTicketStatus());
            preparedStatement.setString(5, ticket.getBranch());
            preparedStatement.setString(6, ticket.getAssignee());
            preparedStatement.setInt(7, ticket.getId());
            databaseConnection.executeUpdate(preparedStatement);


        } catch (DataConnectionException | SQLException e) {
            System.out.println("updating ticket failed with message" + e.getMessage());
        }
    }

}
