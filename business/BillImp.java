package business;

import entity.Bill;
import presentation.ConnectDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillImp implements IBill{
    @Override
    public boolean createBill(Bill bill) {
        Connection conn = null;
        CallableStatement callst = null;
        boolean isCheck = false;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call createBill(?,?,?)}");
            callst.setString(1,bill.getCode());
            callst.setBoolean(2,bill.isType());
            callst.setString(3,bill.getIdCreated());
            callst.executeUpdate();
            isCheck = true;
        }catch (SQLException e){

        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return isCheck;
    }

    @Override
    public List<Bill> getBillIn(int offSet,boolean type,String search) {
        Connection conn = null;
        CallableStatement callst = null;
        List<Bill> billList = null;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call getBillIn(?,?,?)}");
            callst.setInt(1,offSet);
            callst.setBoolean(2,type);
            callst.setString(3,search);
            ResultSet rs = callst.executeQuery();
            billList = new ArrayList<>();
            while(rs.next()){
                Bill bill = new Bill();
                bill.setId(rs.getInt("bill_id"));
                bill.setCode(rs.getString("bill_code"));
                bill.setType(rs.getBoolean("bill_type"));
                bill.setIdCreated(rs.getString("emp_id_created"));
                bill.setCeated(rs.getString("created"));
                bill.setIdAuth(rs.getString("emp_id_auth"));
                bill.setAuthDate(rs.getString("auth_date"));
                bill.setStatus(rs.getInt("bill_status"));
                billList.add(bill);
            }
        }catch (SQLException e){

        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return billList;
    }

    @Override
    public int getLengthBillIn(boolean type,String search) {
        Connection conn = null;
        CallableStatement callst = null;
        int length =0;
        try {
            conn = ConnectDb.oppenConnection();
            callst= conn.prepareCall("{call getLengthBillIn(?,?,?)}");
            callst.registerOutParameter(1, Types.INTEGER);
            callst.setBoolean(2,type);
            callst.setString(3,search);
            callst.execute();
            length = callst.getInt(1);
        }catch (SQLException e){

        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return length;
    }

    @Override
    public List<Bill> getBillInToUpdate(int offSet,boolean type) {
        Connection conn = null;
        CallableStatement callst = null;
        List<Bill> billList = null;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call getBillInToUpdate(?,?)}");
            callst.setInt(1,offSet);
            callst.setBoolean(2,type);
            ResultSet rs = callst.executeQuery();
            billList = new ArrayList<>();
            while(rs.next()){
                Bill bill = new Bill();
                bill.setId(rs.getInt("bill_id"));
                bill.setCode(rs.getString("bill_code"));
                bill.setType(rs.getBoolean("bill_type"));
                bill.setIdCreated(rs.getString("emp_id_created"));
                bill.setCeated(rs.getString("created"));
                bill.setIdAuth(rs.getString("emp_id_auth"));
                bill.setAuthDate(rs.getString("auth_date"));
                bill.setStatus(rs.getInt("bill_status"));
                billList.add(bill);
            }
        }catch (SQLException e){

        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return billList;
    }

    @Override
    public int getLengthBillInToUpdate(boolean type) {
        Connection conn = null;
        CallableStatement callst = null;
        int length =0;
        try {
            conn = ConnectDb.oppenConnection();
            callst= conn.prepareCall("{call getLengthBillInToUpdate(?,?)}");
            callst.registerOutParameter(1, Types.INTEGER);
            callst.setBoolean(2,type);
            callst.execute();
            length = callst.getInt(1);
        }catch (SQLException e){

        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return length;
    }

    @Override
    public boolean updateBillIn(Bill bill) {
        Connection conn = null;
        CallableStatement callst = null;
        boolean isCheck = false;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call updateBillIn(?,?,?,?,?)}");
            callst.setInt(1,bill.getId());
            callst.setString(2,bill.getCode());
            callst.setInt(3,bill.getStatus());
            if (!bill.getIdAuth().equals("null")){
                callst.setString(4,bill.getIdAuth());
            }else {
                callst.setString(4,null);
            }
            callst.setString(5,bill.getAuthDate());
            callst.executeUpdate();
            isCheck = true;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return isCheck;
    }

    @Override
    public Bill findBill(String emp_id,int bill_id) {
        Connection conn = null;
        CallableStatement callst = null;
        Bill bill = null;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call findBill(?,?)}");
            callst.setString(1,emp_id);
            callst.setInt(2,bill_id);
            ResultSet rs = callst.executeQuery();
            while(rs.next()){
                bill = new Bill();
                bill.setId(rs.getInt("bill_id"));
                bill.setCode(rs.getString("bill_code"));
                bill.setType(rs.getBoolean("bill_type"));
                bill.setIdCreated(rs.getString("emp_id_created"));
                bill.setCeated(rs.getString("created"));
                bill.setIdAuth(rs.getString("emp_id_auth"));
                bill.setAuthDate(rs.getString("auth_date"));
                bill.setStatus(rs.getInt("bill_status"));
            }
        }catch (SQLException e){

        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return bill;
    }

    @Override
    public List<Bill> getBillByStatus(int offSet, String empId, boolean billType,String search) {
        Connection conn = null;
        CallableStatement callst = null;
        List<Bill> billList = null;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call getBillByStatus(?,?,?,?)}");
            callst.setInt(1,offSet);
            callst.setBoolean(3,billType);
            callst.setString(2,empId);
            callst.setString(4,search);
            ResultSet rs = callst.executeQuery();
            billList = new ArrayList<>();
            while(rs.next()){
                Bill bill = new Bill();
                bill.setId(rs.getInt("bill_id"));
                bill.setCode(rs.getString("bill_code"));
                bill.setType(rs.getBoolean("bill_type"));
                bill.setIdCreated(rs.getString("emp_id_created"));
                bill.setCeated(rs.getString("created"));
                bill.setIdAuth(rs.getString("emp_id_auth"));
                bill.setAuthDate(rs.getString("auth_date"));
                bill.setStatus(rs.getInt("bill_status"));
                billList.add(bill);
            }
        }catch (SQLException e){

        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return billList;
    }

    @Override
    public int getBillByStatuslength(String empId, boolean type, String search) {
        Connection conn = null;
        CallableStatement callst = null;
        int length =0;
        try {
            conn = ConnectDb.oppenConnection();
            callst= conn.prepareCall("{call getBillByStatusLength(?,?,?,?)}");
            callst.registerOutParameter(4, Types.INTEGER);
            callst.setString(1,empId);
            callst.setBoolean(2,type);
            callst.setString(3,search);
            callst.execute();
            length = callst.getInt(4);
        }catch (SQLException e){

        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return length;
    }
}
