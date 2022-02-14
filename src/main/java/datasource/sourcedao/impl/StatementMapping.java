package datasource.sourcedao.impl;

import datasource.sourcedao.IStatementMapping;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class StatementMapping implements IStatementMapping {
    @Override
    public void mapping(PreparedStatement preparedStatement, Object... member) throws SQLException {
        for(int i=0; i<member.length; i++){
            int index = i + 1;
            Object object = member[i];
            if(object == null){
                preparedStatement.setObject(index, null);
            }else if(object instanceof Integer){
                preparedStatement.setInt(index, (int) object);
            }else if(object instanceof Double){
                preparedStatement.setDouble(index, (double) object);
            } else if(object instanceof String){
                preparedStatement.setString(index, (String) object);
            } else if(object instanceof Date){
                preparedStatement.setTimestamp(index, new Timestamp(((Date) object).getTime()));
            } else if(object instanceof Long){
                preparedStatement.setLong(index, (long) object);
            } else if(object instanceof Boolean){
                preparedStatement.setInt(index, ((boolean)object? 1 : 0) );
            } else{
                preparedStatement.setString(index, object.toString());
            }
        }
    }
}
