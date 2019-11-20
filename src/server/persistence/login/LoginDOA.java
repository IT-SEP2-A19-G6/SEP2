package server.persistence.login;

import server.persistence.database.DatabaseConnection;
import shared.clients.Client;
import shared.exceptions.ClientDisabledException;
import shared.exceptions.IncorrectCredentialsException;

import java.util.ArrayList;

public class LoginDOA implements ILoginDAO{
    private DatabaseConnection databaseConnection;

    public LoginDOA(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    @Override
    public Client loginReq(String username, String pw) throws IncorrectCredentialsException, ClientDisabledException {
        String sql =  "SELECT * FROM " + databaseConnection.getSchemaName() + "." + databaseConnection.getClientTableName() +
                " WHERE username LIKE '" + username + "' AND password LIKE '" + pw + "';";

        ArrayList<Object[]> objects = null;
        objects = databaseConnection.executePreparedQuery(sql);

        if (objects == null){
            throw new IncorrectCredentialsException("Incorrect credentials");
        } else if (objects.size() == 1){
            Object[] obj = objects.get(0);
//            String id = obj[0].toString(); not used //TODO delete or use
            String name = obj[1].toString();
            String password = obj[2].toString();
            boolean active = (boolean) obj[3];

            Client client = new Client(name, password);

            if (active){
                return client; //TODO sort clients by profile
            } else {
                throw new ClientDisabledException("Client is disabled");
            }
        }
        return null;
    }
}
