package shared;

import java.io.Serializable;

public class Request implements Serializable {

    public TYPE type;
    public Object object;


    public enum TYPE {
        LOGIN_REQ, //object contains User
        LOGIN_RESPONSE, //object equals Response
        CLOSE_CONNECTION, //to close socket connection
        TICKET_LIST_REQ, //Object contains TicketListExchange
        TICKET_LIST_RESPONSE, //Object contains TicketListExchange
        OWN_TICKET_LIST_REQ, // Object contains String, username
        ASSIGNED_TICKET_LIST_REQ, // Object contains String, username
        BRANCH_TICKET_LIST_REQ, // Object contains String, username
        NO_TICKETS_FOUND_RESPONSE, //Object contains String "someMessage"
        SIGNUP_REQ,
        SIGNUP_RESPONSE,
        TICKET_CREATE,
        TICKET_RECEIVE,
        SET_STATE // Object contains client type
    }

    public Request(TYPE type, Object object) {
        this.type = type;
        this.object = object;
    }
}
