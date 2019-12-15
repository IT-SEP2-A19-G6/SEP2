package server.persistence.updateticket;

import server.exceptions.DataConnectionException;
import server.persistence.database.IDatabaseConnection;
import shared.Ticket;
import shared.clients.BranchMember;
import shared.clients.ClientType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


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

    @Override
    public ArrayList<BranchMember> getBranchMembersByBranchName(String branchName) throws DataConnectionException {
       String sql = "select * from " + databaseConnection.getClientTableName() + " where branchId = (select id from " + databaseConnection.getBranchTableName() + " where branchName = '" + branchName + "');";
       return getBranchMembers(sql);

    }

    @Override
    public ArrayList<BranchMember> getAllBranchMembers() throws DataConnectionException {
        String sql = "select * from " + databaseConnection.getClientTableName() + " where branchId is not null;";
        return getBranchMembers(sql);
    }

    @Override
    public void setAssignee(Ticket ticket) throws DataConnectionException {
        try {
            String sql = "UPDATE " + databaseConnection.getTicketTableName() +
                    " SET" +
                    " assignee = (select id from account_client where Username = ?)" +
                    " WHERE ticket.id = ?";

            PreparedStatement preparedStatement = databaseConnection.createPreparedStatement(sql);
            preparedStatement.setString(1, ticket.getAssignee());
            preparedStatement.setInt(2, ticket.getId());

            databaseConnection.executeUpdate(preparedStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private ArrayList<BranchMember> getBranchMembers(String preparedSql) throws DataConnectionException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<BranchMember> branchMembers = new ArrayList<>();
        try {
            ps = databaseConnection.executePreparedQuery(preparedSql);
            rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                int branchId = rs.getInt("branchId");

                branchMembers.add(new BranchMember(id, username, ClientType.BRANCH_MEMBER, branchId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.closeConnection(ps, rs);
        }

        return branchMembers;
    }


}
