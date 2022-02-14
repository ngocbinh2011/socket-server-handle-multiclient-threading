import socket.IClientServerTCPProtocol;
import socket.client.IClientSocketConnection;
import socket.client.impl.ClientSocketConnection;
import socket.server.IServerSocketConnection;
import socket.server.impl.ServerSocketConnection;
import socket.server.impl.ServerTCPProtocol;

import java.io.IOException;
import java.net.Socket;
import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerApplication {
    public static void main(String[] args) {

        IClientServerTCPProtocol server = new ServerTCPProtocol();
        server.run();
    }
}
