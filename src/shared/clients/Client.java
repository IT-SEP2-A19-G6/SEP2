package shared.clients;

import java.io.Serializable;

public abstract class Client implements Serializable {

    private String username;
    private String password;


    public Client(String username, String password){
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public abstract String access();

    @Override
    public String toString() {
        return "Username: " + username + "\n" + "Password: " + password;
    }

}
