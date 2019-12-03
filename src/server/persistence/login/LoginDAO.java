package server.persistence.login;

import server.persistence.database.IDatabaseConnection;
import shared.clients.User;
import server.exceptions.DataConnectionException;
import server.exceptions.IncorrectCredentialsException;

import java.util.ArrayList;

public class LoginDAO implements ILoginDAO{
    private IDatabaseConnection databaseConnection;

    public LoginDAO(IDatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    @Override
    public String validateLogin(User user) throws IncorrectCredentialsException, DataConnectionException {
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
            String password = obj[2].toString();
            boolean active = (boolean) obj[3];

            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                if (active){
                    return "User login accepted";
                } else {
                    throw new IncorrectCredentialsException("Incorrect credentials");
                }
            }
        }
        return null;
    }
}
