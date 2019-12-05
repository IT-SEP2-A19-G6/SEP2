package client.model.signup;

import shared.IPropertyChangeSubject;

public interface ISignUpModel extends IPropertyChangeSubject {
    void requestSignUp(String newUsername, String newPassword);
}
