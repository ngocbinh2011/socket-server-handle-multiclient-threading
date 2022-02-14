package datasource.sourcedao;

import java.util.List;


public interface IServiceDAO<T> {

     List<T> query(String sql, IMapper<T> mapper, Object... member);

     boolean insert(String sql, Object... member);

     boolean update(String sql, Object... member);

     boolean delete(String sql, Object... member);
}
