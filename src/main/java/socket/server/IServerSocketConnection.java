package socket.server;

import java.net.Socket;

public interface IServerSocketConnection {
    Socket start() throws Exception;
}
