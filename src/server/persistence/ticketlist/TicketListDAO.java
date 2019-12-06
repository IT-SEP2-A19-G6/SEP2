package server.persistence.ticketlist;

import server.exceptions.DataConnectionException;
import server.persistence.database.IDatabaseConnection;
import shared.Ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            ps = databaseConnection.executePreparedQuery(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("Id");
                int userId = rs.getInt("User_Id");
                String subject = rs.getString("Subject");
                String description = rs.getString("Description");
                String category = rs.getString("Category");
                String location = rs.getString("Location");
                String ticketStatus = rs.getString("Ticket_Status");
                tickets.add(new Ticket(id, userId, subject, description, category, location, ticketStatus));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.closeConnection(ps, rs);
        }
        return tickets;
    }
}
