package datasource.sourcedao;

import java.sql.ResultSet;

public interface IMapper <T>{
    T mapping(ResultSet resultSet);
}
