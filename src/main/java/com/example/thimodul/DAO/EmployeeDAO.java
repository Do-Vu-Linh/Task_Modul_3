package com.example.thimodul.DAO;

import com.example.thimodul.Department;
import com.example.thimodul.Employee;
import com.example.thimodul.myconnection.Myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private Connection connection;

    private final String SELECT_ALL_EMPLOYEE = "select * from employee;";
    private final String DELETE_EMPLOYEE_BY_ID = "delete from employee where id = ?;";
    private final String FIND_EMPLOYEE_BY_NAME = "select * from employee where name like ? ;";

    private final String UPDATE_EMPLOYEE = "update employee set name = ?, email = ?, address = ?, phone_number = ?, salary = ?, department_id = ? where id = ?;";

    private final String FIND_ALL_DEPARTMENT = "select * from department;";
    private final String FIND_DEPARTMENT_BY_ID = "select * from department where id = ?;";
    private final String INSERT_INTO_EMPLOYEE = "insert into employee(name, email, address, phone_number, salary, department_id) value (?, ?, ?, ?,?, ?);";
    private final String FIND_EMPLOYEE_BY_ID = "select * from employee where id = ?;";

    public EmployeeDAO() {
        connection = Myconnection.getConnection();

    }

    public Employee findEmployeetById(int id) {
        Employee employee = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_EMPLOYEE_BY_ID) ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone_number");
                Double salary = resultSet.getDouble("salary");
                int departmentID = resultSet.getInt("department_id");
           employee =  new Employee(id, name, email,address,phone,salary,findDepartmentById(departmentID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
    public Department findDepartmentById(int id) {
        Department departments = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_DEPARTMENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                departments = new Department(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public List<Department> findAllDepartment() {
        List<Department> departments = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_DEPARTMENT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                departments.add(new Department(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public List<Employee> findAllEmployee() {
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEE)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone_number");
                Double salary = resultSet.getDouble("salary");
                int departmentID = resultSet.getInt("department_id");
               employees.add(new Employee(id, name, email,address,phone,salary,findDepartmentById(departmentID)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;

    }
    public boolean insertEmployee(Employee employee) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_EMPLOYEE)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setString(4, employee.getPhone());
            preparedStatement.setDouble(5, employee.getSalary());
            preparedStatement.setInt(6, employee.getDepartment().getId());
           return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteEmployee(int id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE_BY_ID)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateEmployee(Employee employee) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setString(4, employee.getPhone());
            preparedStatement.setDouble(5,employee.getSalary());
            preparedStatement.setInt(6, employee.getDepartment().getId());
            preparedStatement.setLong(7, employee.getId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<Employee> findEmployeeByName(String searchKeyWord) {
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_EMPLOYEE_BY_NAME)) {
            preparedStatement.setString(1,"%" + searchKeyWord +"%");
            getlistSearch(employees, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    private void  getlistSearch(List<Employee> searchemployee, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");
            String phone = resultSet.getString("phone_number");
            Double salary = resultSet.getDouble("salary");
            int departmentID = resultSet.getInt("department_id");
           searchemployee.add(new Employee(id, name, email,address,phone,salary,findDepartmentById(departmentID)));
        }
    }


}
