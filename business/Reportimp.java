package business;

import entity.Report;
import presentation.ConnectDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Reportimp implements IReport{
    @Override
    public List<Report> getReport(int offSet, boolean type) {
        Connection conn = null;
        CallableStatement callst = null;
        List<Report> reportList = null;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call takeReport(?,?)}");
            callst.setInt(1,offSet);
            callst.setBoolean(2,type);
            ResultSet rs = callst.executeQuery();
            reportList = new ArrayList<>();
            while(rs.next()){
                Report report = new Report();
                report.setProductName(rs.getString("product_name"));
                report.setQuantity(rs.getInt("quanity"));
                report.setPrice(rs.getFloat("price"));
                report.setDate(rs.getString("auth_date"));
                report.setBillId(rs.getInt("bill_id"));
                reportList.add(report);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return reportList;
    }

    @Override
    public int getLengthReport(boolean type) {
        Connection conn = null;
        CallableStatement callst = null;
        int length =0;
        try {
            conn = ConnectDb.oppenConnection();
            callst= conn.prepareCall("{call takeLengthReport(?,?)}");
            callst.registerOutParameter(1,Types.INTEGER);
            callst.setBoolean(2,type);
            callst.execute();
            length = callst.getInt(1);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return length;
    }

    @Override
    public List<Report> getReportBetween(int offSet, boolean type,String date1,String date2) {
        Connection conn = null;
        CallableStatement callst = null;
        List<Report> reportList = null;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call takeReportBetween(?,?,?,?)}");
            callst.setInt(1,offSet);
            callst.setBoolean(2,type);
            callst.setString(3,date1);
            callst.setString(4,date2);
            ResultSet rs = callst.executeQuery();
            reportList = new ArrayList<>();
            while(rs.next()){
                Report report = new Report();
                report.setProductName(rs.getString("product_name"));
                report.setQuantity(rs.getInt("quanity"));
                report.setPrice(rs.getFloat("price"));
                report.setDate(rs.getString("auth_date"));
                report.setBillId(rs.getInt("bill_id"));
                reportList.add(report);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return reportList;
    }

    @Override
    public int getLengthReportBetween(boolean type,String date1,String date2) {
        Connection conn = null;
        CallableStatement callst = null;
        int length =0;
        try {
            conn = ConnectDb.oppenConnection();
            callst= conn.prepareCall("{call takeLengthReportBetween(?,?,?,?)}");
            callst.registerOutParameter(1,Types.INTEGER);
            callst.setBoolean(2,type);
            callst.setString(3,date1);
            callst.setString(4,date2);
            callst.execute();
            length = callst.getInt(1);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return length;
    }

    @Override
    public int[] getReportEmployee() {
        Connection conn = null;
        CallableStatement callst = null;
        int[] num = new int[3];
        try {
            conn = ConnectDb.oppenConnection();
            callst= conn.prepareCall("{call takeReportEmployee(?,?,?)}");
            callst.registerOutParameter(1,Types.INTEGER);
            callst.registerOutParameter(2,Types.INTEGER);
            callst.registerOutParameter(3,Types.INTEGER);
            callst.execute();
            num[0] = callst.getInt(1);
            num[1] = callst.getInt(2);
            num[2] = callst.getInt(3);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return num;
    }

    @Override
    public List<Report> getReportBetweenMaxest(int offSet, boolean type, String date1, String date2) {
        Connection conn = null;
        CallableStatement callst = null;
        List<Report> reportList = null;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call takeReportBetweenMaxest(?,?,?,?)}");
            callst.setInt(1,offSet);
            callst.setBoolean(2,type);
            callst.setString(3,date1);
            callst.setString(4,date2);
            ResultSet rs = callst.executeQuery();
            reportList = new ArrayList<>();
            while(rs.next()){
                Report report = new Report();
                report.setProductName(rs.getString("product_name"));
                report.setQuantity(rs.getInt("quanity"));
                report.setPrice(rs.getFloat("price"));
                report.setDate(rs.getString("auth_date"));
                report.setBillId(rs.getInt("bill_id"));
                reportList.add(report);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return reportList;
    }

    @Override
    public List<Report> getReportBetweenMinest(int offSet, boolean type, String date1, String date2) {
        Connection conn = null;
        CallableStatement callst = null;
        List<Report> reportList = null;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call takeReportBetweenMinest(?,?,?,?)}");
            callst.setInt(1,offSet);
            callst.setBoolean(2,type);
            callst.setString(3,date1);
            callst.setString(4,date2);
            ResultSet rs = callst.executeQuery();
            reportList = new ArrayList<>();
            while(rs.next()){
                Report report = new Report();
                report.setProductName(rs.getString("product_name"));
                report.setQuantity(rs.getInt("quanity"));
                report.setPrice(rs.getFloat("price"));
                report.setDate(rs.getString("auth_date"));
                report.setBillId(rs.getInt("bill_id"));
                reportList.add(report);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return reportList;
    }
}
