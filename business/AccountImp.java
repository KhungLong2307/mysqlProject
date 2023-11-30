package business;

import entity.Account;
import presentation.ConnectDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountImp implements IAccount{
    @Override
    public List<Account> getAccount(int offset) {
        Connection conn = null;
        CallableStatement callst = null;
        List<Account> accountList = null;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call getAccount(?)}");
            callst.setInt(1,offset);
            ResultSet rs = callst.executeQuery();
            accountList = new ArrayList<>();
            while(rs.next()){
                Account account = new Account();
                account.setId(rs.getInt("acc_id"));
                account.setUserName(rs.getString("user_name"));
                account.setPassWord(rs.getString("password"));
                account.setPermission(rs.getBoolean("permission"));
                account.setEmpId(rs.getString("emp_id"));
                account.setStatus(rs.getBoolean("acc_status"));
                accountList.add(account);
            }
        }catch (SQLException e){

        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return accountList;
    }

    @Override
    public int getlengthAccount() {
        Connection conn = null;
        CallableStatement callst = null;
        int length = 0;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call getLengthAccount(?)}");
            callst.registerOutParameter(1, Types.INTEGER);
            callst.execute();
            length = callst.getInt(1);
        }catch (SQLException e){

        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return length;
    }

    @Override
    public boolean isCheckUserName(String userName) {
        Connection conn = null;
        CallableStatement callst = null;
        boolean isCheck = true;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call isCheckAccountUserName(?)}");
            callst.setString(1,userName);
            ResultSet rs = callst.executeQuery();
            while(rs.next()){
                isCheck = false;
                break;
            }
        }catch (SQLException e){

        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return isCheck;
    }

    @Override
    public boolean isCheckEmpId(String empid) {
        Connection conn = null;
        CallableStatement callst = null;
        boolean isCheck = true;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call isCheckAccountEmpId(?)}");
            callst.setString(1,empid);
            ResultSet rs = callst.executeQuery();
            while (rs.next()){
                isCheck = false;
                break;
            }
        }catch (SQLException e ){

        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return isCheck;
    }

    @Override
    public boolean createAccount(Account account) {
        Connection conn = null;
        CallableStatement callst = null;
        boolean isCheck = false;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call createAccount(?,?,?,?,?)}");
            callst.setString(1,account.getUserName());
            callst.setString(2,account.getPassWord());
            callst.setBoolean(3,account.isPermission());
            callst.setString(4,account.getEmpId());
            callst.setBoolean(5,account.isStatus());
            callst.executeUpdate();
            isCheck = true;
        }catch (SQLException e){

        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return isCheck;
    }

    @Override
    public Account findAccout(int id) {
        Connection conn = null;
        CallableStatement callst = null;
        Account account = null;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call findAccountStatus(?)}");
            callst.setInt(1,id);
            ResultSet rs = callst.executeQuery();
            while(rs.next()){
                account = new Account();
                account.setId(rs.getInt("acc_id"));
                account.setUserName(rs.getString("user_name"));
                account.setPassWord(rs.getString("password"));
                account.setPermission(rs.getBoolean("permission"));
                account.setEmpId(rs.getString("emp_id"));
                account.setStatus(rs.getBoolean("acc_status"));
            }
        }catch (SQLException e){

        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return account;
    }

    @Override
    public boolean updateStatus(Account account) {
        Connection conn = null;
        CallableStatement callst = null;
        boolean isCheck = false;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call updateAccountStatus(?,?)}");
            callst.setInt(1,account.getId());
            callst.setBoolean(2,account.isStatus());
            callst.executeUpdate();
            isCheck = true;
        }catch (SQLException e){

        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return isCheck;
    }

    @Override
    public List<Account> getAccountSearch(int offSet, String search) {
        Connection conn = null;
        CallableStatement callst = null;
        List<Account> accountList = null;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call searchAccount(?,?)}");
            callst.setInt(1,offSet);
            callst.setString(2,search);
            ResultSet rs = callst.executeQuery();
            accountList = new ArrayList<>();
            while(rs.next()){
                Account account = new Account();
                account.setId(rs.getInt("acc_id"));
                account.setUserName(rs.getString("user_name"));
                account.setPassWord(rs.getString("password"));
                account.setPermission(rs.getBoolean("permission"));
                account.setEmpId(rs.getString("emp_id"));
                account.setStatus(rs.getBoolean("acc_status"));
                accountList.add(account);
            }
        }catch (SQLException e){

        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return accountList;
    }

    @Override
    public int getlengthSearch(String search) {
        Connection conn = null;
        CallableStatement callst = null;
        int length = 0;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call searchAccountLength(?,?)}");
            callst.setString(1,search);
            callst.registerOutParameter(2, Types.INTEGER);
            callst.execute();
            length = callst.getInt(2);
        }catch (SQLException e){

        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return length;
    }
}
