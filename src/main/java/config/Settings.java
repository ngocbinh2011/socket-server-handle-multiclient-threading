package config;


public class Settings {

    private static Settings instance;

    public final String DATABASE_URL = "jdbc:ucanaccess://C:\\WorkSpace\\access_db\\products.accdb";
    public final int MAX_CONNECTION = 10;
    public final int SERVER_SOCKET_PORT = 24131;
    public final String SERVER_SOCKET_HOST = "127.0.0.1";

    static {
        instance = new Settings();
    }


    public static Settings getInstance(){
        return instance;
    }


}
