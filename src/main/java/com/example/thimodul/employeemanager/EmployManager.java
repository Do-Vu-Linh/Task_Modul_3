package com.example.thimodul.employeemanager;

import com.example.thimodul.DAO.EmployeeDAO;
import com.example.thimodul.Department;
import com.example.thimodul.Employee;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EmployManager {
    private EmployeeDAO employeeDAO;
public EmployManager() {
    employeeDAO = new EmployeeDAO();
}
    public List<Employee> findAllEmployees(HttpServletRequest request) {
        return employeeDAO.findAllEmployee();

    }
    public List<Department> findAllDepartment(HttpServletRequest request) {
        return employeeDAO.findAllDepartment();
    }
    public boolean save(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        Double salary = Double.parseDouble(request.getParameter("salary"));
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));

        if (id == null) {
            return employeeDAO.insertEmployee(new Employee(name, email,address,phone,salary,employeeDAO.findDepartmentById(departmentId)));
        } else {
           return employeeDAO.updateEmployee(new Employee(Integer.parseInt(id),name, email,address,phone,salary,employeeDAO.findDepartmentById(departmentId)));
        }
    }
    public boolean deleteEmployee(HttpServletRequest request) {
        String id = request.getParameter("id");
        return employeeDAO.deleteEmployee(Integer.parseInt(id));
    }


    public Employee findEmployeeById (HttpServletRequest request) {
    int id = Integer.parseInt(request.getParameter("id"));
        return employeeDAO.findEmployeetById(id);
    }
    public List<Employee> searchEmployeeByName(HttpServletRequest request) {
        String name = request.getParameter("search");
        return employeeDAO.findEmployeeByName(name);
    }
}
