package testdummies;

import server.exceptions.DataConnectionException;
import server.persistence.database.IDatabaseConnection;
import shared.clients.User;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class TestDatabaseConnection implements IDatabaseConnection {
    User testUser;
    User disabledUser;
    ArrayList<Object[]> usersToReturn;

    public TestDatabaseConnection() {
        testUser = new User("correctUser", "correctPassword");
        disabledUser = new User("disabledUser", "correctPassword");
        usersToReturn = new ArrayList<>();
    }


    @Override
    public ArrayList<Object[]> executePreparedQuery(String preparedSql) {
        if (preparedSql.equals("SELECT * FROM " + getSchemaName() + "." + getClientTableName() +
                " WHERE username LIKE '" + testUser.getUsername() + "' AND password LIKE '" + testUser.getPassword() + "';")) {
            Object[] userFromDb = {1, testUser.getUsername(), testUser.getPassword(), true};
            usersToReturn.add(userFromDb);
        } else if (preparedSql.equals("SELECT * FROM " + getSchemaName() + "." + getClientTableName() +
                " WHERE username LIKE '" + disabledUser.getUsername() + "' AND password LIKE '" + disabledUser.getPassword() + "';")) {
            Object[] userFromDb = {2, disabledUser.getUsername(), disabledUser.getPassword(), false};
            usersToReturn.add(userFromDb);
        }
        return usersToReturn;
    }

    @Override
    public PreparedStatement createPreparedStatement(String preparedSql) throws DataConnectionException {
        return null;
    }

    @Override
    public void executeUpdate(PreparedStatement preparedStatement) {

    }

    @Override
    public String getSchemaName() {
        return "sep2";
    }

    @Override
    public String getClientTableName() {
        return "account_client";
    }

    @Override
    public String getTicketTableName() {
        return null;
    }

    @Override
    public String getUserTableName() {
        return null;
    }
}
