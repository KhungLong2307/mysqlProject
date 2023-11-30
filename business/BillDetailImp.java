package business;

import entity.Bill;
import entity.BillDetail;
import presentation.ConnectDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BillDetailImp implements IBillDetail{
    @Override
    public boolean createBillDetail(BillDetail billDetail) {
        Connection conn = null;
        CallableStatement callst = null;
        boolean isCheck = false;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call createBillDetail(?,?,?,?)}");
            callst.setInt(1,billDetail.getBillId());
            callst.setString(2,billDetail.getProductId());
            callst.setInt(3,billDetail.getQuantity());
            callst.setFloat(4,billDetail.getPrice());
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
    public int takeBillId(Bill bill) {
        Connection conn = null;
        CallableStatement callst = null;
        int billId = 0;
        try {
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call takeBillId(?,?)}");
            callst.setString(1,bill.getIdCreated());
            callst.registerOutParameter(2, Types.INTEGER);
            callst.execute();
            billId = callst.getInt(2);
        }catch (SQLException e){

        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return billId;
    }

    @Override
    public List<BillDetail> getBillDetailToUpdate(int offSet,int billId) {
        Connection conn = null;
        CallableStatement callst = null;
        List<BillDetail> billDetailList = null;
        try {
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call getBillDetailToUpdate(?,?)}");
            callst.setInt(1,offSet);
            callst.setInt(2,billId);
            billDetailList = new ArrayList<>();
            ResultSet rs =  callst.executeQuery();
            while(rs.next()){
                BillDetail billDetail = new BillDetail();
                billDetail.setProductName(rs.getString("product_name"));
                billDetail.setId(rs.getInt("bill_detail_id"));
                billDetail.setBillId(rs.getInt("bill_id"));
                billDetail.setProductId(rs.getString("product_id"));
                billDetail.setQuantity(rs.getInt("quanity"));
                billDetail.setPrice(rs.getFloat("price"));
                billDetailList.add(billDetail);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return billDetailList;
    }

    @Override
    public int getBillDetailToUpdateLength(int billId) {
        Connection conn = null;
        CallableStatement callst = null;
        int length =0;
        try {
            conn = ConnectDb.oppenConnection();
            callst= conn.prepareCall("{call getBillDetailToUpdateLength(?,?)}");
            callst.registerOutParameter(1, Types.INTEGER);
            callst.setInt(2,billId);
            callst.execute();
            length = callst.getInt(1);
        }catch (SQLException e){

        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return length;
    }

    @Override
    public boolean updateBillDetail(BillDetail billDetail) {
        Connection conn = null;
        CallableStatement callst = null;
        boolean isCheck = false;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call updateBillDetail(?,?,?)}");
            callst.setInt(1,billDetail.getId());
            callst.setInt(2,billDetail.getQuantity());
            callst.setFloat(3,billDetail.getPrice());
            callst.executeUpdate();
            isCheck = true;
        }catch (SQLException e){
        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return isCheck;
    }
}
