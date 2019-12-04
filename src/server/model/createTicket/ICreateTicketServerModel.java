package server.model.createTicket;

import shared.CreateTicket;
import shared.Response;

public interface ICreateTicketServerModel {
    Response writeToTable(CreateTicket createTicket);
}
