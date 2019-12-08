package server.persistence.database;

import server.exceptions.DataConnectionException;
import shared.util.ApplicationProperties;

import java.sql.*;

public class DatabaseConnection implements IDatabaseConnection {
    private String schemaName;
    private String clientTableName;
    private String ticketTableName;
    private String userTableName;
    private String branchTableName;

    private String driver = ApplicationProperties.INSTANCE.getDbDriver();
    private String url = ApplicationProperties.INSTANCE.getDbUrl();
    private String user = ApplicationProperties.INSTANCE.getDbUser();
    private String pw = ApplicationProperties.INSTANCE.getDbPassword();

    private Connection connection;

    public DatabaseConnection() {
        schemaName = "sep2";
        clientTableName = "account_client"; // TODO: Perhaps this could be placed in an enum later?
        userTableName = "account_user";
        ticketTableName = "ticket";
        branchTableName = "branch";

    }


    public Connection getConnection() throws DataConnectionException {

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, user, pw);
            }
        } catch (SQLException e) {
            throw new DataConnectionException("Failed to establish connection to data");
        }
        return connection;
    }

    @Override
    public void closeConnection(PreparedStatement ps, ResultSet rs) {
        // close preparedstatement
        try {
            if (rs != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // close the resultset
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // close connection
        try {
            if (!connection.isClosed())
                getConnection().close();
        } catch (SQLException | DataConnectionException e) {
            e.printStackTrace();
        }
    }



    @Override
    public PreparedStatement executePreparedQuery(String preparedSql) throws DataConnectionException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            throw new DataConnectionException("Lost connection to data");
        }
        assert preparedStatement != null;
        return preparedStatement;

    }


    @Override
    public PreparedStatement createPreparedStatement(String preparedSql) throws DataConnectionException  {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            throw new DataConnectionException("Lost connection to data");
        }
        return preparedStatement;
    }
    @Override
    public void executeUpdate(PreparedStatement preparedStatement){
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getSchemaName(){
        return schemaName;
    }
    @Override
    public String getClientTableName(){
        return clientTableName;
    }
    @Override
    public String getTicketTableName() {
        return ticketTableName;
    }
    @Override
    public String getUserTableName(){
        return userTableName;
    }

    @Override
    public String getBranchTableName() {
        return branchTableName;
    }

}