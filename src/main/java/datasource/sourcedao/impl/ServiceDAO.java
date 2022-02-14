package datasource.sourcedao.impl;

import datasource.sourcedao.IConnection;
import datasource.sourcedao.IMapper;
import datasource.sourcedao.IServiceDAO;
import datasource.sourcedao.IStatementMapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class ServiceDAO<T> implements IServiceDAO<T> {

    private IConnection connector = new AccessDBConnection();

    private IStatementMapping statementMapping = new StatementMapping();


    @Override
    public List<T> query(String sql, IMapper<T> mapper, Object... member) {
        List<T> result = new ArrayList<>();
        Connection connection = connector.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            statementMapping.mapping(preparedStatement, member);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(mapper.mapping(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    protected boolean modify(String sql, Object... member) {
        Connection connection = connector.getConnection();
        boolean isSuccess = false;
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            statementMapping.mapping(preparedStatement, member);
            preparedStatement.execute();
            connection.commit();
            isSuccess = true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    @Override
    public boolean insert(String sql, Object... member) {
        return modify(sql, member);
    }

    @Override
    public boolean update(String sql, Object... member) {
        return modify(sql, member);
    }

    @Override
    public boolean delete(String sql, Object... member) {
        return modify(sql, member);
    }


}
