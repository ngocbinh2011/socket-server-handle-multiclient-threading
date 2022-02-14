package socket.server.impl;

import socket.IClientServerTCPProtocol;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerTCPProtocol implements IClientServerTCPProtocol {
    @Override
    public void run() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocketConnection serverSocketConnection = new ServerSocketConnection(executorService);
        try {
            serverSocketConnection.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
