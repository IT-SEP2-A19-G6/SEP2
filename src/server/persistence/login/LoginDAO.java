package server.persistence.login;

import server.exceptions.DataConnectionException;
import server.persistence.database.IDatabaseConnection;
import shared.clients.Client;
import shared.clients.ClientType;
import shared.clients.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO implements ILoginDAO{
    private IDatabaseConnection databaseConnection;

    public LoginDAO(IDatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }


    @Override
    public Client validateLogin(User user) throws DataConnectionException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        User resultUser = null;
        try {
            String sql =  "SELECT * FROM " + databaseConnection.getSchemaName() + "." + databaseConnection.getClientTableName() + " WHERE username LIKE '" + user.getUsername() + "' AND password LIKE '" + user.getPassword() + "';";
            ps = databaseConnection.executePreparedQuery(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("Id");
                String userName = rs.getString("Username");
                String password = null;
                boolean active = rs.getBoolean("Active"); // needs to be in user
                ClientType type =  ClientType.valueOf(rs.getString("type"));
                resultUser = new User(id, userName, password, type, active);
            }
        } catch (SQLException e) {
            throw new DataConnectionException(e.getMessage());
        } finally {
            databaseConnection.closeConnection(ps, rs);
        }
        return resultUser;
    }



}
