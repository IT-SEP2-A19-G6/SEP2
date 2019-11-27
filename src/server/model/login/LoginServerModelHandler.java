package server.model.login;

/* SOMEone edited?
import server.persistence.DataFactory;

public class LoginServerModelHandler implements LoginServerModel {
    DataFactory dataFactory;

    public LoginServerModelHandler(DataFactory dataFactory) {
        this.dataFactory = dataFactory;
    }
*/


import server.persistence.login.ILoginDAO;
import shared.Response;
import shared.clients.User;
import shared.exceptions.DataConnectionException;
import shared.exceptions.IncorrectCredentialsException;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginServerModelHandler implements ILoginServerModel {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ILoginDAO loginDAO;

    public LoginServerModelHandler(ILoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @Override
    public Response validateLogin(User user){
        String loginMessage;
        try {
            loginMessage = loginDAO.validateLogin(user);
        } catch (IncorrectCredentialsException | DataConnectionException e) {
            loginMessage = e.getMessage();
        }
        return new Response(user.getUsername(), loginMessage);
    }

    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        if (name == null){
            support.addPropertyChangeListener(listener);
        } else {
            support.addPropertyChangeListener(name, listener);
        }
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        if (name == null){
            support.removePropertyChangeListener(listener);
        } else {
            support.removePropertyChangeListener(name, listener);
        }
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

}
