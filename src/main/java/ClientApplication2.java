import config.Settings;
import socket.IClientServerTCPProtocol;
import socket.client.IClientSocketConnection;
import socket.client.impl.ClientSocketConnection;
import socket.client.impl.ClientTCPProtocol;

import java.net.Socket;

public class ClientApplication2 {
    public static void main(String[] args) {
        IClientServerTCPProtocol client = new ClientTCPProtocol();
        client.run();

    }
}
