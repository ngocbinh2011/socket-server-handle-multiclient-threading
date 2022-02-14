package socket.client.impl;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import socket.IMessageTranformer;
import socket.client.IClientSocketConnection;
import config.Settings;
import util.Scannable;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocketConnection implements IClientSocketConnection, IMessageTranformer {

    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    public ClientSocketConnection(String host, int port) throws IOException {
        socket = new Socket(host, port);

        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public Socket connect() throws IOException {
        System.out.println("Connecting...");
        System.out.printf("Connect success to Server on port %d!\n", socket.getLocalPort());
        System.out.println("-----------------------------------");
        boolean isQuit = false;
        while (!isQuit){
            System.out.print("Please enter your command: ");
            Scanner scanner = Scannable.getScanner();
            String command = scanner.nextLine();
            if(command.equals("quit")){
                isQuit = true;
            }
            sendMessage(command);
            String result = getMessage();
            System.out.println(String.format("Server Response for command '%s': %s", command, result));
        }

        return socket;
    }

    @Override
    public void sendMessage(String message) throws IOException {
        bufferedWriter.flush();
        bufferedWriter.write(message);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    @Override
    public String getMessage() throws IOException {
        return bufferedReader.readLine();
    }
}
