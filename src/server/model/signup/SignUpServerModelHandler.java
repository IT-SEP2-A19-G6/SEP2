package server.model.signup;


import server.exceptions.DataConnectionException;
import server.persistence.signup.ISignUpDAO;
import shared.Response;
import shared.clients.User;

public class SignUpServerModelHandler implements ISignUpServerModel {
    private final ISignUpDAO signUpDAO;

    public SignUpServerModelHandler(ISignUpDAO signUpDAO) {
        this.signUpDAO = signUpDAO;
    }

    @Override
    public Response requestSignUp(User newUser) {
        String DAOResponse;
        try {
            DAOResponse = signUpDAO.requestSignUp(newUser);
        } catch (DataConnectionException e) {
            DAOResponse = e.getMessage();
        }
        return new Response(newUser, DAOResponse);
    }
}
