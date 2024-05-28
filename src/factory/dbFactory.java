package factory;

import java.sql.*;

public class dbFactory {

    private static Connection conn;

    private final static String url = "jdbc:mysql://127.0.0.1:3306/pet_sync";
    private final static String host = "root";
    private final static String pass = "";

    public static void createConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");

        if(conn == null){
            conn = DriverManager.getConnection(url, host, pass);
        }else{
            if(!conn.isClosed())
                conn.close();
            conn = null;
            createConnection();
        }
    }

    public static Connection getConn() {
        return conn;
    }

    public static void setConn(Connection conn) {
        dbFactory.conn = conn;
    }
}
