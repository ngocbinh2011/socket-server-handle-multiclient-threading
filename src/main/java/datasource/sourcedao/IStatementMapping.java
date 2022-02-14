package datasource.sourcedao;


import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface IStatementMapping {
    void mapping(PreparedStatement preparedStatement, Object... member) throws SQLException;
}
