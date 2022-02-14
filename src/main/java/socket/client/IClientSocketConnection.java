package socket.client;

import java.net.Socket;

public interface IClientSocketConnection {
    Socket connect() throws Exception;
}
