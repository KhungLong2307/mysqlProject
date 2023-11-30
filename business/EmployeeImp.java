package business;

import entity.Employee;
import presentation.ConnectDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeImp implements IEmployee{
    @Override
    public List<Employee> getEmployee(int offset) {
        Connection conn = null;
        CallableStatement callst = null;
        List<Employee> employeeList = null;
        try{
            conn = ConnectDb.oppenConnection();
            callst= conn.prepareCall("{call getEmployee(?)}");
            callst.setInt(1,offset);
            ResultSet rs = callst.executeQuery();
            employeeList = new ArrayList<>();
            while (rs.next()){
             Employee employee = new Employee();
             employee.setId(rs.getString("emp_id"));
             employee.setName(rs.getString("emp_name"));
             employee.setBirthDay(rs.getString("birth_of_date"));
             employee.setEmail(rs.getString("email"));
             employee.setPhone(rs.getString("phone"));
             employee.setAdress(rs.getString("address"));
             employee.setStatus(rs.getInt("emp_status"));
             employeeList.add(employee);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return employeeList;
    }

    @Override
    public int getLengthEmployee() {
        Connection conn = null;
        CallableStatement callst = null;
        int length = 0;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call employeeLength(?)}");
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
    public boolean isCheckId(String id) {
        Connection conn = null;
        CallableStatement callst = null;
        boolean isCheck = true;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call employeeCheckId(?)}");
            callst.setString(1,id);
            ResultSet rs = callst.executeQuery();
            while(rs.next()){
                isCheck = false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return isCheck;
    }

    @Override
    public boolean isCheckEmail(String email) {
        Connection conn = null;
        CallableStatement callst = null;
        boolean isCheck = true;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call employeeCheckEmail(?)}");
            callst.setString(1,email);
            ResultSet rs = callst.executeQuery();
            while (rs.next()){
                isCheck = false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return isCheck;
    }

    @Override
    public boolean createEmployee(Employee employee) {
        Connection conn = null;
        CallableStatement callst = null;
        boolean isCheck = false;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call createEmployee(?,?,?,?,?,?,?)}");
            callst.setString(1,employee.getId());
            callst.setString(2,employee.getName());
            callst.setString(3,employee.getBirthDay());
            callst.setString(4,employee.getEmail());
            callst.setString(5,employee.getPhone());
            callst.setString(6,employee.getAdress());
            callst.setInt(7,employee.getStatus());
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
    public Employee updateById(String id) {
        Connection conn = null;
        CallableStatement callst = null;
        Employee employee = null;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call findUpdateById(?)}");
            callst.setString(1,id);
            ResultSet rs =callst.executeQuery();
            while(rs.next()){
                employee = new Employee();
                employee.setId(rs.getString("emp_id"));
                employee.setName(rs.getString("emp_name"));
                employee.setBirthDay(rs.getString("birth_of_date"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone(rs.getString("phone"));
                employee.setAdress(rs.getString("address"));
                employee.setStatus(rs.getInt("emp_status"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return employee;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        Connection conn = null;
        CallableStatement callst = null;
        boolean isCheck = false;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call updateEmployee(?,?,?,?,?,?,?)}");
            callst.setString(1,employee.getId());
            callst.setString(2,employee.getName());
            callst.setString(3,employee.getBirthDay());
            callst.setString(4,employee.getEmail());
            callst.setString(5,employee.getPhone());
            callst.setString(6,employee.getAdress());
            callst.setInt(7,employee.getStatus());
            callst.executeUpdate();
            isCheck = true;
        }catch (SQLException e){

        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return isCheck;
    }

    @Override
    public int getLengthSearch(String search) {
        Connection conn = null;
        CallableStatement callst = null;
        int length = 0;
        try{
            conn = ConnectDb.oppenConnection();
            callst = conn.prepareCall("{call getLengthSearch(?,?)}");
            callst.setString(1,search);
            callst.registerOutParameter(2,Types.INTEGER);
            callst.execute();
            length = callst.getInt(2);
        }catch (SQLException e){

        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return length;
    }

    @Override
    public List<Employee> getEmployeeAfterSearch(int offset, String search) {
        Connection conn = null;
        CallableStatement callst = null;
        List<Employee> employeeList = null;
        try{
            conn = ConnectDb.oppenConnection();
            callst= conn.prepareCall("{call getEmployeeAfterSearch(?,?)}");
            callst.setInt(1,offset);
            callst.setString(2,search);
            ResultSet rs = callst.executeQuery();
            employeeList = new ArrayList<>();
            while (rs.next()){
                Employee employee = new Employee();
                employee.setId(rs.getString("emp_id"));
                employee.setName(rs.getString("emp_name"));
                employee.setBirthDay(rs.getString("birth_of_date"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone(rs.getString("phone"));
                employee.setAdress(rs.getString("address"));
                employee.setStatus(rs.getInt("emp_status"));
                employeeList.add(employee);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDb.closeConnection(conn,callst);
        }
        return employeeList;
    }
}
