package datasource.sourcedao.impl;

import config.Settings;
import datasource.sourcedao.IDatasource;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class AccessBasicDataSource implements IDatasource {

    private static BasicDataSource basicDataSource;

    public AccessBasicDataSource(){

    }

    @Override
    public DataSource getDatasource() {
        if(basicDataSource == null){
            synchronized (AccessBasicDataSource.class){
                if(basicDataSource == null){
                    Settings settings = Settings.getInstance();
                    basicDataSource = new BasicDataSource();
                    basicDataSource.setUrl(settings.DATABASE_URL);
                    basicDataSource.setMaxTotal(settings.MAX_CONNECTION);
                }
            }
        }
        return basicDataSource;
    }


}
