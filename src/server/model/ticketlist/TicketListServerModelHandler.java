package server.model.ticketlist;

import server.exceptions.DataConnectionException;
import server.persistence.ticketlist.ITicketListDAO;
import shared.Request;
import shared.Ticket;
import shared.TicketListExchange;

import java.util.ArrayList;

public class TicketListServerModelHandler implements ITicketListServerModel {
    private ITicketListDAO ticketListDAO;

    public TicketListServerModelHandler(ITicketListDAO userDAO) {
        this.ticketListDAO = userDAO;
    }

    @Override
    public TicketListExchange requestTicketList(TicketListExchange exchange) {
        if (exchange.getAction().equals(Request.TYPE.OWN_TICKET_LIST_REQ)){
            try {
                exchange.setTicketList(ticketListDAO.getOwnTicketList(exchange.getUsername()));
                if (exchange.getTickets().size() == 0){
                    exchange.setAction(Request.TYPE.NO_TICKETS_FOUND_RESPONSE);
                    exchange.setMessage("No tickets yet - try add one...");
                } else {
                    exchange.setAction(Request.TYPE.OWN_TICKET_LIST_RESPONSE);
                }
            } catch (DataConnectionException e) {
                e.printStackTrace();
            }
        } else if (exchange.getAction().equals(Request.TYPE.ASSIGNED_TICKET_LIST_REQ)){
            try {
                exchange.setTicketList(ticketListDAO.getAssignedTicketList(exchange.getUsername()));
                if (exchange.getTickets().size() == 0){
                    exchange.setAction(Request.TYPE.NO_TICKETS_ASSIGNED_RESPONSE);
                    exchange.setMessage("Good job!! go fetch more @branch.");
                } else {
                    exchange.setAction(Request.TYPE.ASSIGNED_TICKET_LIST_RESPONSE);
                }
            } catch (DataConnectionException e) {
                e.printStackTrace();
            }
        } else if (exchange.getAction().equals(Request.TYPE.BRANCH_TICKET_LIST_REQ)){
            try {
                exchange.setTicketList(ticketListDAO.getBranchTicketList(exchange.getUsername()));
                if (exchange.getTickets().size() == 0){
                    exchange.setAction(Request.TYPE.NO_TICKETS_IN_BRANCH_RESPONSE);
                    exchange.setMessage("No tickets!! relax until more arrives...");
                } else {
                    exchange.setAction(Request.TYPE.BRANCH_TICKET_LIST_RESPONSE);
                }
            } catch (DataConnectionException e) {
                e.printStackTrace();
            }
        }
        return exchange;
    }
}
