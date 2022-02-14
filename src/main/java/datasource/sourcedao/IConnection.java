package datasource.sourcedao;

import java.sql.Connection;

public interface IConnection {
    Connection getConnection();
}
