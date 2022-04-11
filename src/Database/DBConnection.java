package Database;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;



public abstract class DBConnection {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; //local
    private static final String driver = "com.mysql.cj.jdbc.Driver"; //Driver reference
    private static final String userName = "sqlUser"; //Username
    private static final String password = "Passw0rd!"; //Password
    public static Connection conn; //Connection Interface



    public static Connection startConnection(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(jdbcUrl, userName, password); //Reference Conncection object

            System.out.println("Connection successful");
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return conn;
    }


    public static Connection getConnection(){
        return conn;
    }


    public static void closeConnection(){
        try{
            conn.close();
        }catch (Exception e){

        }
    }



}
