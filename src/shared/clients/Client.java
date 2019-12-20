package shared.clients;

import java.io.Serializable;

public abstract class Client implements Serializable {

    private int id;
    private final String username;
    private String password;
    private ClientType type;


    Client(String username, String password) {
        this.username = username;
        this.password = password;
    }

    Client(int id, String username, ClientType type) {
        this.id = id;
        this.username = username;
        this.type = type;
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

    public int getClientId() {
        return id;
    }


}