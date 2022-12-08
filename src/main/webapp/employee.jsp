<%--
  Created by IntelliJ IDEA.
  User: X
  Date: 08/12/2022
  Time: 10:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nhân Viên </title>
</head>
<body>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/28/2022
  Time: 3:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <style>
        h1 {
            margin-left: 500px;
        }

        a {
            color: black;
            text-decoration: none;
            font-weight: bold;
        }

        input {
            border-radius: 4px;
        }

        form {
            float: right;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>List Employee</h1>
    <h2><c:out value="${message}"/></h2>
    <a href="${pageContext.request.contextPath}/EmployeeServlet?action=create">
        <button style="margin-bottom: 10px" class="btn btn-primary">
            Add new employee
        </button>
    </a>
    <form action="EmployeeServlet?action=search" method="post">
        <input type="text" name="search" placeholder="Enter name search">
        <button class="btn btn-primary">Search</button>
    </form>
    <table class="table table-striped">
        <tr>
            <th>STT</th>
            <th>Name</th>
            <th>Email</th>
            <th>Address</th>
            <th>Phone</th>
            <th>Salary</th>
            <th>Department</th>
            <th colspan="2" style="text-align: center; width: 25%">Action</th>
        </tr>
        <c:forEach items="${employees}" var="employees">
            <tr>
                <td><c:out value="${employees.getId()}"/></td>
                <td><c:out value="${employees.getName()}"/></td>
                <td><c:out value="${employees.getEmail()}"/></td>
                <td><c:out value="${employees.getAddress()}"/></td>
                <td><c:out value="${employees.getPhone()}"/></td>
                <td><c:out value="${employees.getSalary()}"/></td>
                <td><c:out value="${employees.getDepartment().getName()}"/></td>
                <td>
                    <a href="EmployeeServlet?action=update&id=${employees.getId()}">
                        <button class="btn btn-warning">
                            Update
                        </button>
                    </a>
                </td>
                <td>
                    <a href="EmployeeServlet?action=delete&id=${employees.getId()}" onclick="return test('${employees.getName()}')">
                        <button class="btn btn-danger">
                            Delete
                        </button>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
<script>
    function test(name) {
        return confirm("Bạn có chắc chắn muốn xóa sản phẩm: " + name + "?")
    }
</script>
</html>


</body>
</html>
