package socket.threading;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import datasource.dao.IProductDAO;
import datasource.dao.impl.ProductDAO;
import jdk.internal.util.xml.impl.Input;
import model.Product;
import socket.IMessageTranformer;
import socket.server.exception.CommandNotFoundException;
import socket.server.impl.Command;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ServerTaskProcessRunnable implements Runnable, IMessageTranformer {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public ServerTaskProcessRunnable(Socket socket) throws IOException {
        this.socket = socket;
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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

    @Override
    public void run() {
        boolean isQuit = false;
        try {
            IProductDAO productDAO = new ProductDAO();
            while (!isQuit) {
                String commandString = getMessage();
                Thread thread = Thread.currentThread();
                System.out.println(
                        String.format("Server processing... [command: %s - thread: %s - client: %s]",
                                commandString, thread.getName(), socket.getInetAddress())
                );
                commandString = commandString.trim();
                String cmd = commandString;
                if (commandString.contains(" ")) {
                    cmd = commandString.trim().substring(0, commandString.indexOf(" "));
                }
                String arr[] = commandString.split(" ");
                Command command = null;
                try {
                    command = Command.getCommand(cmd);
                    switch (command) {
                        case SAVE_PRODUCT:
                            if(productDAO.isExists(arr[1])){
                                sendMessage(String.format("[Product Id - %s] already exists!", arr[1]));
                                continue;
                            }
                            Product product = new Product(arr[1], arr[2], Double.parseDouble(arr[3]));
                            productDAO.save(product);
                            sendMessage(product.toString());
                            break;

                        case DELETE_BY_ID:
                            productDAO.delete(arr[1]);
                            sendMessage("Delete success!");
                            break;

                        case FIND_BY_ID:
                            Product product1 = productDAO.findById(arr[1]);
                            sendMessage(product1.toString());
                            break;

                        case FIND_BY_NAME:
                            List<Product> list = productDAO.findByName(arr[1]);
                            sendMessage(list.toString());
                            break;

                        case EDIT_PRICE:
                            Product product2 = productDAO.findById(arr[1]);
                            product2.setPrice(Double.parseDouble(arr[2]));
                            productDAO.update(product2);
                            sendMessage("Edit success " + product2.toString());
                            break;

                        case QUIT:
                            isQuit = true;
                            sendMessage("Server closed! Thanks for use");
                            break;
                    }
                } catch (CommandNotFoundException e) {
                    sendMessage("Your command not supported! Please try again");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
