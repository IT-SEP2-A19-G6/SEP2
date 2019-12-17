package server.persistence.database;

import server.exceptions.DataConnectionException;
import shared.util.ApplicationProperties;

import java.sql.*;

public class DatabaseConnection implements IDatabaseConnection {

    private final String schemaName;
    private final String clientTableName;
    private final String ticketTableName;
    private final String branchTableName;
    private final String replyTableName;

    private final String driver = ApplicationProperties.INSTANCE.getDbDriver();
    private final String url = ApplicationProperties.INSTANCE.getDbUrl();
    private final String user = ApplicationProperties.INSTANCE.getDbUser();
    private final String pw = ApplicationProperties.INSTANCE.getDbPassword();

    private Connection connection;

    public DatabaseConnection() {
        schemaName = "sep2";
        clientTableName = "account_client";
        ticketTableName = "ticket";
        branchTableName = "branch";
        replyTableName = "reply";
    }


    private Connection getConnection() throws DataConnectionException {

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
        try {
            if (rs != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        PreparedStatement preparedStatement;
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

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            throw new DataConnectionException("Lost connection to data");
        }
        return preparedStatement;
    }
    @Override
    public void executeUpdate(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.executeUpdate();
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
    public String getBranchTableName() {
        return branchTableName;
    }
    @Override
    public String getReplyTableName() {return replyTableName;}


}