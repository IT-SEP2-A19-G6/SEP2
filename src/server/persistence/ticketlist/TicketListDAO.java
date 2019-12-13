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
    public ArrayList<Ticket> getOwnTicketList(String userToFind) throws DataConnectionException {
        String sql = "SELECT t.id, created_at, c.Username as creator, Subject, Description, Category, Location, Ticket_Status, branchName, abm.Username as assignee FROM " + databaseConnection.getTicketTableName() + " t " +
        "INNER JOIN Account_Client c ON t.User_Id = c.id " +
        "INNER JOIN account_branch_member abm on t.Assignee = abm.Id " +
        "INNER JOIN Branch B on abm.Id_Branch = B.Id " +
        "WHERE c.Username = '" + userToFind + "' " +
        "GROUP by t.Id, c.id, abm.id, b.id;";

        return getTickets(sql);
    }


    @Override
    public ArrayList<Ticket> getAssignedTicketList(String userToFind) throws DataConnectionException {
        String sql = "SELECT t.id, created_at, u.Username as creator, Subject, Description, Category, Location, Ticket_Status, branchName, c.Username as assignee FROM " + databaseConnection.getTicketTableName() + " t " +
                "INNER JOIN Account_Client c ON t.assignee = c.id " +
                "INNER JOIN account_user u on t.user_Id = u.Id " +
                "INNER JOIN Branch b on t.Id_Branch = b.Id " +
                "WHERE c.Username = '" + userToFind + "' " +
                "GROUP by t.Id, c.id, u.id, b.id;";

        return getTickets(sql);
    }

    @Override
    public ArrayList<Ticket> getBranchTicketList(String userToFind) throws DataConnectionException {
        String sql = "SELECT t.id, created_at, u.Username as creator, Subject, Description, Category, Location, Ticket_Status, branchName, c.Username as assignee FROM " + databaseConnection.getTicketTableName() + " t " +
                "INNER JOIN account_branch_member abm ON t.Id_Branch = abm.Id_Branch " +
                "INNER JOIN account_branch_member c ON t.assignee = c.id " +
                "INNER JOIN Branch B ON t.Id_Branch = B.Id " +
                "INNER JOIN Account_User u ON t.user_Id = u.Id " +
                "WHERE abm.username = '" + userToFind + "';";

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
                int id = rs.getInt("Id");
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
