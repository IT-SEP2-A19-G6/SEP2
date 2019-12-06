package server.persistence.database;

import server.exceptions.DataConnectionException;
import shared.util.ApplicationProperties;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection implements IDatabaseConnection {
    private String schemaName;
    private String clientTableName;
    private String ticketTableName;
    private String userTableName;

    public DatabaseConnection() {
        schemaName = "sep2";
        clientTableName = "account_client"; // TODO: Perhaps this could be placed in an enum later?
        userTableName = "account_user";
        ticketTableName = "ticket";

    }


    private Connection getConnection() throws DataConnectionException {
        String driver = ApplicationProperties.INSTANCE.getDbDriver();
        String url = ApplicationProperties.INSTANCE.getDbUrl();
        String user = ApplicationProperties.INSTANCE.getDbUser();
        String pw = ApplicationProperties.INSTANCE.getDbPassword();

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pw);
        } catch (SQLException e) {
            throw new DataConnectionException("Failed to establish connection to data");
        }

        return connection;
    }

    private void closeConnection(Connection connection, PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null)
                preparedStatement.close();
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

    public ArrayList<Object[]> executePreparedQuery(String preparedSql) throws DataConnectionException {
        ArrayList<Object[]> results = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            throw new DataConnectionException("Lost connection to data");
        }

        ResultSet resultSet = null;
        try {
            assert preparedStatement != null;
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                results.add(row);
            }
        } catch (SQLException e) {
            throw new DataConnectionException("Lost connection to data");
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(connection, preparedStatement);
        }
        return results;
    }

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

    public void executeUpdate(PreparedStatement preparedStatement){
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String getSchemaName(){
        return schemaName;
    }

    public String getClientTableName(){
        return clientTableName;
    }

    public String getTicketTableName() {
        return ticketTableName;
    }

    public String getUserTableName(){
        return userTableName;
    }

}