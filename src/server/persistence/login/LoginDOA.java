package server.persistence.login;

import server.persistence.database.DatabaseConnection;
import shared.clients.Client;
import shared.clients.User;
import shared.exceptions.LoginDisabledException;
import shared.exceptions.IncorrectCredentialsException;

import java.util.ArrayList;

public class LoginDOA implements ILoginDAO{
    private DatabaseConnection databaseConnection;

    public LoginDOA(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    @Override
    public String validateLogin(User user) throws IncorrectCredentialsException, LoginDisabledException {
        String sql =  "SELECT * FROM " + databaseConnection.getSchemaName() + "." + databaseConnection.getClientTableName() +
                " WHERE username LIKE '" + user.getUsername() + "' AND password LIKE '" + user.getPassword() + "';";

        ArrayList<Object[]> objects = null;
        objects = databaseConnection.executePreparedQuery(sql);

        if (objects.size() == 0){
            throw new IncorrectCredentialsException("Incorrect credentials");
        } else if (objects.size() == 1){
            Object[] obj = objects.get(0);
//            String id = obj[0].toString(); //TODO delete or use
            String username = obj[1].toString();
//            String password = obj[2].toString(); /TODO delete or use
            boolean active = (boolean) obj[3];

            if (active){
                return "user login accepted";
            } else {
                throw new LoginDisabledException("account disabled");
            }
        }
        return null;
    }
}
