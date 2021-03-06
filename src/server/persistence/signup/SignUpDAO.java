package server.persistence.signup;

import server.exceptions.DataConnectionException;
import server.persistence.database.IDatabaseConnection;
import shared.clients.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SignUpDAO implements ISignUpDAO{
    private final IDatabaseConnection databaseConnection;

    public SignUpDAO(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public String requestSignUp(User newUser) throws DataConnectionException {

        try {
            String sql = "insert into " + databaseConnection.getClientTableName() + " (Username, Password) VALUES (?, ?);";

            PreparedStatement preparedStatement = databaseConnection.createPreparedStatement(sql);

            preparedStatement.setString(1, newUser.getUsername());
            preparedStatement.setString(2, newUser.getPassword());

            databaseConnection.executeUpdate(preparedStatement);
            return "Success";
        } catch (SQLException e) {
            return "Username already taken!";
        }
    }

}
