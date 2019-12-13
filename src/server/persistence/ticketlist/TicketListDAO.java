package server.persistence.ticketlist;

import server.exceptions.DataConnectionException;
import server.persistence.database.IDatabaseConnection;
import shared.Ticket;
import shared.clients.BranchMember;
import shared.clients.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
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
    public ArrayList<Ticket> getOwnTicketList(int searchId) throws DataConnectionException {
        String sql = "SELECT t.id AS ticketId, created_at, Subject, Description, Location, Ticket_Status, c.username AS creator, branchName, a.Username AS assignee FROM " + databaseConnection.getTicketTableName() + " t " +
        "INNER JOIN Account_Client c ON t.client_Id = c.Id " +
        "INNER JOIN branch b ON t.Id_Branch = b.Id " +
        "LEFT JOIN  account_client a ON t.assignee = a.Id " +
        "WHERE c.id = " + searchId +
        "ORDER BY created_at DESC;";

        return getTickets(sql);
    }


    @Override
    public ArrayList<Ticket> getAssignedTicketList(int searchId) throws DataConnectionException {
        String sql = "SELECT t.id AS ticketId, created_at, Subject, Description, Location, Ticket_Status, c.username AS creator, branchName, a.Username AS assignee FROM " + databaseConnection.getTicketTableName() + " t " +
                "INNER JOIN Account_Client a ON t.assignee = a.Id " +
                "INNER JOIN branch b ON t.Id_Branch = b.Id " +
                "LEFT JOIN  account_client c ON t.client_Id = c.Id " +
                "WHERE a.id = " + searchId +
                "ORDER BY created_at ASC;";

        return getTickets(sql);
    }

    @Override
    public ArrayList<Ticket> getBranchTicketList(int searchId) throws DataConnectionException {
        String sql = "SELECT t.id AS ticketId, created_at, Subject, Description, Location, Ticket_Status, c.username AS creator, branchName, a.Username AS assignee  FROM " + databaseConnection.getTicketTableName() + " t " +
                "LEFT JOIN Account_Client a ON t.Assignee = a.Id " +
                "INNER JOIN Account_Client c ON t.client_Id = c.id " +
                "INNER JOIN branch b ON t.Id_Branch = b.Id " +
                "WHERE t.id_branch = " + searchId +
                "ORDER BY created_at ASC;";

        return getTickets(sql);
    }


    private ArrayList<Ticket> getTickets(String preparedSql) throws DataConnectionException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            ps = databaseConnection.executePreparedQuery(preparedSql);
            rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("ticketId");
                Date createdDate = rs.getTimestamp("created_at");
                String creator = rs.getString("creator");
                String subject = rs.getString("Subject");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String ticketStatus = rs.getString("Ticket_Status");
                String branch = rs.getString("branchname");
                String assignee = rs.getString("assignee");

                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                String date = dateFormat.format(createdDate);

                tickets.add(new Ticket(id, date, creator, subject, description, location, ticketStatus, branch, assignee));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.closeConnection(ps, rs);
        }

        return tickets;
    }


}
