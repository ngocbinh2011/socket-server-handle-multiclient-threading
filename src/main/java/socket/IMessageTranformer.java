package socket;

import java.io.IOException;

public interface IMessageTranformer {
    void sendMessage(String message) throws IOException;
    String getMessage() throws IOException;
}
