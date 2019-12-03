package server.model.signup;


import server.persistence.signup.ISignUpDAO;
import shared.Response;
import shared.clients.User;

public class SignUpServerModelHandler implements ISignUpServerModel {
    private ISignUpDAO signUpDAO;

    public SignUpServerModelHandler(ISignUpDAO signUpDAO) {
        this.signUpDAO = signUpDAO;
    }

    @Override
    public Response requestSignUp(User newUser) {
        return signUpDAO.requestSignUp(newUser);
    }
}
