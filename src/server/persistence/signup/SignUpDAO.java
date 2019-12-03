package server.persistence.signup;

import server.persistence.database.IDatabaseConnection;
import shared.Response;
import shared.clients.User;


public class SignUpDAO implements ISignUpDAO{
    private IDatabaseConnection databaseConnection;

    public SignUpDAO(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public Response requestSignUp(User newUser) {
        Response response;
        //TODO delete below - Just for test - do JDBC
        User userToCheck = new User("user", "user");
        if (newUser.getUsername().equals(userToCheck.getUsername())){
            response = new Response(newUser.getUsername(), "Username already taken");
        } else {
            response = new Response(newUser.getUsername(), "ok");
        }
        return response;
    }

}
