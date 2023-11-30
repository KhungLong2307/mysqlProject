package business;

import entity.Employee;

import java.util.List;

public interface IEmployee {
    List<Employee> getEmployee(int offset);
    int getLengthEmployee();
    boolean isCheckId(String id);
    boolean isCheckEmail(String email);
    boolean createEmployee(Employee employee);
    Employee updateById(String id);
    boolean updateEmployee(Employee employee);

    int getLengthSearch(String search);
    List<Employee> getEmployeeAfterSearch(int offset,String search);
}
