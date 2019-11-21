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
    public Client validateLogin(User user) throws IncorrectCredentialsException, LoginDisabledException {
        String sql =  "SELECT * FROM " + databaseConnection.getSchemaName() + "." + databaseConnection.getClientTableName() +
                " WHERE username LIKE '" + user.getUsername() + "' AND password LIKE '" + user.getPassword() + "';";

        ArrayList<Object[]> objects = null;
        objects = databaseConnection.executePreparedQuery(sql);

        if (objects.size() == 0){
            throw new IncorrectCredentialsException("Incorrect credentials");
        } else if (objects.size() == 1){
            Object[] obj = objects.get(0);
//            String id = obj[0].toString(); //TODO delete or use
            String name = obj[1].toString();
            String password = obj[2].toString();
            boolean active = (boolean) obj[3];

            Client client = new User(obj[1].toString(), obj[2].toString()); //TODO might need some client type attr. in db

            if (active){
                return client; //TODO sort clients by profile
            } else {
                throw new LoginDisabledException("Client is disabled");
            }
        }
        return null;
    }
}
