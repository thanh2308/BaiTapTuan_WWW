<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 9/23/2026
  Time: 1:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Danh sách tài khoản</title>
</head>
<body>
<h2>Danh sách tài khoản đã đăng ký</h2>
<a href="RegisterForm.jsp" class="btn-add">Đăng ký thêm tài khoản</a>

<table border="1" width="80%">
    <thead>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Date of Birth</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="acc" items="${accounts}">
        <tr>
            <td>${acc.id}</td>
            <td>${acc.firstname}</td>
            <td>${acc.lastname}</td>
            <td>${acc.email}</td>
            <td><fmt:formatDate value="${acc.dateOfBirth}" pattern="dd/MM/yyyy" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>