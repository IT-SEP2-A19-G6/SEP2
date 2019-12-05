package shared;

import java.io.Serializable;

public class Request implements Serializable {

    public TYPE type;
    public Object object;


    public enum TYPE {
        LOGIN_REQ, //object contains User
        LOGIN_RESPONSE, //object equals Response
        CLOSE_CONNECTION, //to close socket connection
        TICKET_LIST_REQ, // Object contains String, username
        TICKET_LIST_RESPONSE, //Object contains ArrayList<Ticket>
        NO_TICKETS_FOUND_RESPONSE, //Object contains String "someMessage"
        SIGNUP_REQ,
        SIGNUP_RESPONSE,
        TICKET, // object contains new ticket
        TICKET_CREATE,
        TICKET_RECEIVE
    }

    public Request(TYPE type, Object object) {
        this.type = type;
        this.object = object;
    }
}
