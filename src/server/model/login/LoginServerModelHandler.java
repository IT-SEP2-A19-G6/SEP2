package server.model.login;

/* SOMEone edited?
import server.persistence.DataFactory;

public class LoginServerModelHandler implements LoginServerModel {
    DataFactory dataFactory;

    public LoginServerModelHandler(DataFactory dataFactory) {
        this.dataFactory = dataFactory;
    }
*/


import server.persistence.DataFactory;
import server.persistence.login.ILoginDAO;
import shared.Request;
import shared.clients.Client;
import shared.clients.User;
import shared.exceptions.IncorrectCredentialsException;
import shared.exceptions.LoginDisabledException;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginServerModelHandler implements LoginServerModel{
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ILoginDAO loginDAO;

    public LoginServerModelHandler(DataFactory dataFactory) {
        loginDAO = dataFactory.getLoginDOA();
    }


    @Override
    public void validateLogin(User userToValidate){
        Client user = null;
        try {
            user = loginDAO.validateLogin(userToValidate);
        } catch (IncorrectCredentialsException e) {
            System.out.println(e.getMessage()); //TODO handle Exception correct
        } catch (LoginDisabledException e) {
            System.out.println(e.getMessage()); //TODO handle Exception correct
        }
        if (user != null){
            support.firePropertyChange(Request.TYPE.LOGIN_ACCEPT.name(), "", user);
        }

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
