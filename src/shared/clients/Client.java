package shared.clients;

import java.io.Serializable;

public abstract class Client implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private ClientType type;


    public Client(String username, String password, ClientType type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public Client(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ClientType getType() {
        return type;
    }

    public Integer getUser_id() {
        return id;
    }

    public abstract void setType(ClientType type);

}