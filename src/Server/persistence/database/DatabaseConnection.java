package Server.persistence.database;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {
    private String schemaName;
    private String clientTableName;

    public DatabaseConnection(){
        schemaName = "sep2";
        clientTableName = "account_client";
    }

    private Connection getConnection(){ //TODO update postgres credentials
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String pw = "IT-DBS1-A19-JN";

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pw);
        } catch (SQLException e) {
            e.printStackTrace();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Object[]> executePreparedQuery(String preparedSql) {
        ArrayList<Object[]> results = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet resultSet = null;
        try {
            assert preparedStatement != null;
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                results.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

    public String getSchemaName(){
        return schemaName;
    }

    public String getClientTableName(){
        return clientTableName;
    }


}
