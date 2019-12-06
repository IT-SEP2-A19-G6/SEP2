package shared.clients;

import java.io.Serializable;

public abstract class Client implements Serializable {

    private int id;
    private String username;
    private String password;
    private String type;
    private boolean active;


    public Client(int id, String username, String password, String type, boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
        this.active = active;
    }

    public Client(String username, String password, String type) {
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

    public String getType() {
        return type;
    }

    public boolean getActive() {
        return active;
    }

    public int getUser_id() {
        return id;
    }

    public abstract void setType(String type);

}