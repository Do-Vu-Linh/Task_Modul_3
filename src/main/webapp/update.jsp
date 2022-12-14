<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/28/2022
  Time: 4:03 PM
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
        div {
            margin: auto;
            width: 500px;
        }
    </style>
</head>
<body>
<div>
    <h1>Create form</h1>
    <form method="post" action = "/EmployeeServlet?action=updatefinal">

        <input type="hidden" name="id" value="<c:out value="${e.getId()}"/>" />
        <div class="mb-3 mt-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" placeholder="Enter name" value="<c:out value="${e.getName()}" />" name="name">
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">Email</label>
            <input type="text" class="form-control" id="price" placeholder="Enter Email" value="<c:out value="${e.getEmail()}" />" name="email">
        </div>
        <div class="mb-3">
            <label for="address" class="form-label">Address</label>
            <input type="text" class="form-control" id="address" placeholder="Enter address" value="<c:out value="${e.getAddress()}" />" name="address">
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">Phone</label>
            <input type="text" class="form-control" id="phone" placeholder="Enter phone" value="<c:out value="${e.getPhone()}" />" name="phone">
        </div> <div class="mb-3">
        <label for="salary" class="form-label">Salary</label>
        <input type="text" class="form-control" id="salary" placeholder="Enter salary" value="<c:out value="${e.getSalary()}" />" name="salary">
    </div>
        <div class="mb-3">
            <label for="departmentId" class="form-label">Departmanet </label>
            <select class="form-control" id="departmentId" name="departmentId">
                <c:forEach items="${department}" var="c">
                    <option value="${c.getId()}"><c:out value="${c.getName()}"/></option>
                </c:forEach>

            </select>
        </div>
        <button type="submit" class="btn btn-primary">Create</button>
        <a href="EmployeeServlet">
            <button type="button" class="btn btn-secondary">Cancel</button>
        </a>
    </form>
</div>
</body>
</html>

