package shared.clients;

import java.io.Serializable;

public abstract class Client implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private String type;


    public Client(String username, String password, String type){
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public Integer getUser_id() {
        return id;
    }

    public abstract void setType(String type);