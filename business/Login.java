package business;

import entity.Account;
import presentation.ConnectDb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
        public Account login(String userName, String passWord){
            Connection conn = null;
            CallableStatement callst = null;
            Account login = null;
            try{
                conn = ConnectDb.oppenConnection();
                callst = conn.prepareCall("{call login(?,?)}");
                callst.setString(1,userName);
                callst.setString(2,passWord);
                ResultSet rs = callst.executeQuery();
                while(rs.next()){
                    login = new Account();
                    login.setId(rs.getInt("acc_id"));
                    login.setUserName(rs.getString("user_name"));
                    login.setPassWord(rs.getString("password"));
                    login.setPermission(rs.getBoolean("permission"));
                    login.setEmpId(rs.getString("emp_id"));
                    login.setStatus(rs.getBoolean("acc_status"));
                }
            }catch (SQLException e){
            }finally {
                ConnectDb.closeConnection(conn,callst);
            }
            return login;
        }
}
