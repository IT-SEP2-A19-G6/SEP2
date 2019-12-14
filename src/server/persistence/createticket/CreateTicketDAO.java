package server.persistence.createticket;

import server.exceptions.DataConnectionException;
import server.persistence.database.IDatabaseConnection;
import shared.Branch;
import shared.Ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreateTicketDAO implements ICreateTicketDAO {

    private IDatabaseConnection databaseConnection;
    public CreateTicketDAO(IDatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    @Override
    public String addTicket(Ticket createTicket) throws DataConnectionException {

        try {
            String sql = "INSERT INTO " + databaseConnection.getTicketTableName() + " (client_id, subject, description, location, ticket_Status, id_branch)" + " VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = databaseConnection.createPreparedStatement(sql);

            preparedStatement.setInt(1, createTicket.getClientId());
            preparedStatement.setString(2, createTicket.getSubject());
            preparedStatement.setString(3, createTicket.getDescription());
            preparedStatement.setString(4, createTicket.getLocation());
            preparedStatement.setString(5, "OPEN");
            preparedStatement.setInt(6, createTicket.getBranchId());
            databaseConnection.executeUpdate(preparedStatement);
            return "OK";
        } catch (SQLException e) {
            System.out.println("Creating ticket failed, no ID obtained" + e.getMessage());
        }
        return "NOT OK";
    }

    @Override
    public ArrayList<Branch> getBranches() throws DataConnectionException {
        String sql = "SELECT * FROM " + databaseConnection.getBranchTableName() + ";";

        return getBranches(sql);
    }

    @Override
    public void updateTicket(Ticket ticket) {
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

    private ArrayList<Branch> getBranches(String preparedSql) throws DataConnectionException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Branch> branches = new ArrayList<>();
        try {
            ps = databaseConnection.executePreparedQuery(preparedSql);
            rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String branchName = rs.getString("branchname");


                branches.add(new Branch(id, branchName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.closeConnection(ps, rs);
        }

        return branches;
    }

}
