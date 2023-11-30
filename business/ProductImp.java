package business;

import entity.Product;
import presentation.ConnectDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductImp implements IProduct{

    @Override
    public List<Product> getProduct(int offSet) {
        Connection conn = null;
        CallableStatement callst = null;
        List<Product> productList = null;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call getProduct(?)}");
            callst.setInt(1,offSet);
            ResultSet rs = callst.executeQuery();
            productList = new ArrayList<>();
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getString("product_id"));
                product.setName(rs.getString("product_name"));
                product.setManufacturer(rs.getString("manufacturer"));
                product.setCreated(rs.getString("created"));
                product.setBatch(rs.getInt("batch"));
                product.setQuantity(rs.getInt("quantity"));;
                product.setStatus(rs.getBoolean("product_status"));
                productList.add(product);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            ConnectDb.closeConnection(conn,callst);
        }
        return productList;
    }

    @Override
    public int getProductLength() {
        Connection conn = null;
        CallableStatement callst = null;
        int length = 0;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call getproductLength(?)}");
            callst.registerOutParameter(1, Types.INTEGER);
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
    public boolean productCheckName(String name) {
        Connection conn = null;
        CallableStatement callst = null;
        boolean ischeck = true;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call productCheckName(?)}");
            callst.setString(1,name);
            ResultSet rs = callst.executeQuery();
            while(rs.next()){
                ischeck = false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return ischeck;
    }

    @Override
    public boolean productCheckId(String id) {
        Connection conn = null;
        CallableStatement callst = null;
        boolean ischeck = true;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call productCheckId(?)}");
            callst.setString(1,id);
            ResultSet rs = callst.executeQuery();
            while(rs.next()){
                ischeck = false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return ischeck;
    }

    @Override
    public boolean productCheckBatch(int batch) {
        Connection conn = null;
        CallableStatement callst = null;
        boolean ischeck = true;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call productCheckBatch(?)}");
            callst.setInt(1,batch);
            ResultSet rs = callst.executeQuery();
            while(rs.next()){
                ischeck = false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return ischeck;
    }

    @Override
    public boolean createProduct(Product product) {
        Connection conn = null;
        CallableStatement callst = null;
        boolean isCheck = false;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call createProduct(?,?,?,?,?)}");
            callst.setString(1, product.getId());
            callst.setString(2,product.getName());
            callst.setString(3,product.getManufacturer());
            callst.setInt(4,product.getBatch());
            callst.setBoolean(5,product.isStatus());
            callst.executeUpdate();
            isCheck = true;
        }catch (SQLException e){
        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return isCheck;
    }

    @Override
    public Product findProduct(String id) {
        Connection conn = null;
        CallableStatement callst = null;
        Product product = null;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call findProduct(?)}");
            callst.setString(1,id);
            ResultSet rs = callst.executeQuery();
            while(rs.next()){
                product = new Product();
                product.setId(rs.getString("product_id"));
                product.setName(rs.getString("product_name"));
                product.setManufacturer(rs.getString("manufacturer"));
                product.setBatch(rs.getInt("batch"));
                product.setQuantity(rs.getInt("quantity"));
            }
        }catch (SQLException e){
        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return product;
    }

    @Override
    public boolean updateProduct(Product product) {
        Connection conn = null;
        CallableStatement callst = null;
        boolean isCheck = false;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call updateProduct(?,?,?,?,?,?)}");
            callst.setString(1, product.getId());
            callst.setString(2,product.getName());
            callst.setString(3,product.getManufacturer());
            callst.setInt(4,product.getBatch());
            callst.setBoolean(5,product.isStatus());
            callst.setInt(6,product.getQuantity());
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
    public List<Product> getSearchProduct(int offSet, String name) {
        Connection conn = null;
        CallableStatement callst = null;
        List<Product> productList = null;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call searchProduct(?,?)}");
            callst.setInt(1,offSet);
            callst.setString(2,name);
            ResultSet rs = callst.executeQuery();
            productList = new ArrayList<>();
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getString("product_id"));
                product.setName(rs.getString("product_name"));
                product.setManufacturer(rs.getString("manufacturer"));
                product.setCreated(rs.getString("created"));
                product.setBatch(rs.getInt("batch"));
                product.setQuantity(rs.getInt("quantity"));;
                product.setStatus(rs.getBoolean("product_status"));
                productList.add(product);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            ConnectDb.closeConnection(conn,callst);
        }
        return productList;
    }

    @Override
    public int getSearchProductLength(String name) {
        Connection conn = null;
        CallableStatement callst = null;
        int length = 0;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call searchProductLength(?,?)}");
            callst.setString(2,name);
            callst.registerOutParameter(1, Types.INTEGER);
            callst.execute();
            length = callst.getInt(1);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return length;
    }

}
