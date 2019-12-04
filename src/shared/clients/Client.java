package shared.clients;

import java.io.Serializable;

public abstract class Client implements Serializable {

    private Integer user_id;
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

    public Integer getUser_id() {return user_id;}


}
