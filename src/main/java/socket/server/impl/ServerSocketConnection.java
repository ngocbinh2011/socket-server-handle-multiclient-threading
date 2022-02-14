package socket.server.impl;

import config.Settings;
import socket.server.IServerSocketConnection;
import socket.threading.ServerTaskProcessRunnable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class ServerSocketConnection implements IServerSocketConnection {

    private ExecutorService executorService;

    public ServerSocketConnection() {

    }

    public ServerSocketConnection(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public Socket start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(Settings.getInstance().SERVER_SOCKET_PORT);
        System.out.println(String.format("Server running on %d. Ready for connect...!", Settings.getInstance().SERVER_SOCKET_PORT));
        System.out.println("-----------------------------------");
        while(true){
            Socket socket = serverSocket.accept();
            System.out.printf("INFO: One client %s connected to server.\n", socket.toString());
            executorService.submit(new ServerTaskProcessRunnable(socket));

        }

    }
}
