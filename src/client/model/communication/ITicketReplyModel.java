package client.model.communication;

import shared.IPropertyChangeSubject;

public interface ITicketReplyModel extends IPropertyChangeSubject {
    void getReplies(int ticketId);
    @SuppressWarnings("SpellCheckingInspection")
    void addReply(int ticketid, String message);
}
