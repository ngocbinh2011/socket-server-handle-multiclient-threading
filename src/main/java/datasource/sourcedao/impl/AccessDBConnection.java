package datasource.sourcedao.impl;

import datasource.sourcedao.IConnection;
import datasource.sourcedao.IDatasource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class AccessDBConnection implements IConnection {
    private static IDatasource accessDatasource = new AccessBasicDataSource();

    @Override
    public Connection getConnection() {
        DataSource dataSource = accessDatasource.getDatasource();
        synchronized (dataSource){
            try {
                return dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
