package Database;

import java.sql.*;

public class DBQuery {

    private static PreparedStatement statement;


    public static void setPreparedStatement(Connection connection, String sqlStatement) throws SQLException{
        statement = connection.prepareStatement(sqlStatement);
    }

    public static PreparedStatement getPreparedStatement(){
        return statement;
    }

}
