package shared.clients;

import java.io.Serializable;

public abstract class Client implements Serializable {

    private int id;
    private String username;
    private String password;
    private ClientType type;
    private boolean active;


    public Client(int id, String username, String password, ClientType type, boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
        this.active = active;
    }

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

    public boolean getActive() {
        return active;
    }

    public int getUserId() {
        return id;
    }

    public abstract void setType(ClientType type);

}