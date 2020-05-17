package server.persistence.login;

import server.exceptions.DataConnectionException;
import server.persistence.database.IDatabaseConnection;
import shared.clients.BranchMember;
import shared.clients.Client;
import shared.clients.ClientType;
import shared.clients.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO implements ILoginDAO{
    private final IDatabaseConnection databaseConnection;

    public LoginDAO(IDatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }


    @Override
    public Client validateLogin(User user) throws DataConnectionException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Client resultUser = null;
        try {
            String sql = "SELECT id, username, branchId FROM " + databaseConnection.getSchemaName()
                    + "." + databaseConnection.getClientTableName() + " WHERE username LIKE '" + user.getUsername()
                    + "' AND password LIKE '" + user.getPassword() + "';";
            ps = databaseConnection.executePreparedQuery(sql);rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String userName = rs.getString("username");
                int branchId = rs.getInt("branchId");

                if (branchId == 0){
                    resultUser = new User(id, userName, ClientType.USER);
                } else {
                    resultUser = new BranchMember(id, userName, ClientType.BRANCH_MEMBER, branchId);
                }
            }
        } catch (SQLException e) {
            throw new DataConnectionException(e.getMessage());
        } finally {
            databaseConnection.closeConnection(ps, rs);
        }
        return resultUser;
    }



}
