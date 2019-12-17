package client.model.createticket;

import shared.IPropertyChangeSubject;

public interface ICreateTicketModel extends IPropertyChangeSubject {

    void submitTicket(String subject, String category, String description, String location);

    void getBranches();
}
