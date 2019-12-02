package shared;

import java.io.Serializable;

public class Request implements Serializable {

    public TYPE type;
    public Object object;


    public enum TYPE {
        LOGIN_REQ, //object equals User
        LOGIN_RESPONSE, //object equals Response
        CLOSE_CONNECTION, //to close socket connection
        TICKET_SEND,
        TICKET_RECEIVE
    }

    public Request(TYPE type, Object object) {
        this.type = type;
        this.object = object;
    }
}
