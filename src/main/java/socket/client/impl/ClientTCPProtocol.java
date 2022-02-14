package socket.client.impl;

import config.Settings;
import socket.IClientServerTCPProtocol;
import socket.client.IClientSocketConnection;

import java.io.IOException;
import java.net.Socket;

public class ClientTCPProtocol implements IClientServerTCPProtocol {
    @Override
    public void run() {
        Socket client = null;
        try {
            IClientSocketConnection clientSocketConnection = new ClientSocketConnection(
                    Settings.getInstance().SERVER_SOCKET_HOST, Settings.getInstance().SERVER_SOCKET_PORT
            );
            client = clientSocketConnection.connect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
