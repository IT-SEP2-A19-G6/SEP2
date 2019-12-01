package client.network.socket;

import shared.IPropertyChangeSubject;
import shared.Request;

public interface IClientSocketHandler extends Runnable, IPropertyChangeSubject {
    void sendToServer(Request request);
    void closeConnection();
}
