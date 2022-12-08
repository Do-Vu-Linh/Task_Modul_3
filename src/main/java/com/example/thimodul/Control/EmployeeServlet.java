package com.example.thimodul.Control;

import com.example.thimodul.Department;
import com.example.thimodul.Employee;
import com.example.thimodul.employeemanager.EmployManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeeServlet", value = "/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    private EmployManager employManager;

    @Override
    public void init() {

        employManager = new EmployManager();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                createForm(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            case "update":
                updateForm(request, response);


                break;
            default:
                findAllEmployee(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                addNewEmployee(request, response);
                break;
            case "updatefinal":
                update(request, response);
                break;
            case "search":
                searchname(request, response);

                break;
        }
    }

    public void findAllEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employees = employManager.findAllEmployees(request);
        List<Department> departments = employManager.findAllDepartment(request);
        request.setAttribute("employees", employees);
        request.setAttribute("departments", departments);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("employee.jsp");
        requestDispatcher.forward(request, response);
    }

    public void addNewEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        employManager.save(request);
        response.sendRedirect("/EmployeeServlet");

    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        employManager.save(request);
        response.sendRedirect("/EmployeeServlet");

    }

    public void createForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> departments = employManager.findAllDepartment(request);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("create.jsp");
        request.setAttribute("departments", departments);
        requestDispatcher.forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        employManager.deleteEmployee(request);
        response.sendRedirect("/EmployeeServlet");
    }

    private void updateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = employManager.findEmployeeById(request);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("update.jsp");
        request.setAttribute("e", employee);
        request.setAttribute("department", employManager.findAllDepartment(request));
        requestDispatcher.forward(request, response);
    }
    public void searchname(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("employee.jsp");
        request.setAttribute("employees",employManager.searchEmployeeByName(request));
        requestDispatcher.forward(request, response) ;
    }
}
