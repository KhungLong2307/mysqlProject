package presentation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDb {
    private static final String drive = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/warehouse_management";
    private static final String user = "root";
    private static final String pass = "Duongtran321";
    public static Connection oppenConnection(){
        Connection conn = null;
        try{
            Class.forName(drive);
            conn = DriverManager.getConnection(url,user,pass);
        }catch (ClassNotFoundException | SQLException cnfe){
            System.out.println(cnfe);
        }
        return conn;
    }
    public static void closeConnection(Connection conn, CallableStatement callst){
        if(conn!=null){
            try{
                conn.close();
            }catch (SQLException sqle){
                System.out.println(sqle);
            }
        }
        if(callst!=null){
            try{
                callst.close();
            }catch (SQLException sqle){
                System.out.println(sqle);
            }
        }
    }
}
