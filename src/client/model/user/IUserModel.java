package client.model.user;

import shared.IPropertyChangeSubject;

public interface IUserModel extends IPropertyChangeSubject {
    void requestTicketList(String username);

//    void addTicket(); //TODO delete or use
}
