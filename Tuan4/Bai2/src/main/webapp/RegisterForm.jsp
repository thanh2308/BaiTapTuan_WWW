<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 9/23/2026
  Time: 1:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>User Registration Form</title>
</head>
<body>
<h2>User Registration Form</h2>
<form action="${pageContext.request.contextPath}/registerform" method="post">
  <div>
    <label>First Name:</label>
    <input type="text" name="firstname" required>
  </div>
  <br>
  <div>
    <label>Last Name:</label>
    <input type="text" name="lastname" required>
  </div>
  <br>
  <div>
    <label>Your Email:</label>
    <input type="text" name="email" required>
  </div>
  <br>
  <div>
    <label>Password:</label
    <input type="password" name="password" required>
  </div>
  <br>
  <div>
    <label>Birthday:</label>
    <select name="day" style="width: 70px;">
      <% for (int i = 1; i <= 31; i++) { %>
      <option value="<%= i %>"><%= i %></option>
      <% } %>
    </select>
    <select name="month" style="width: 70px;">
      <% for (int i = 1; i <= 12; i++) { %>
      <option value="<%= i %>">Tháng <%= i %></option>
      <% } %>
    </select>
    <select name="year" style="width: 90px;">
      <% for (int i = 2026; i >= 1970; i--) { %>
      <option value="<%= i %>"><%= i %></option>
      <% } %>
    </select>
  </div>
  <br>
  <div>
    <label>Gender:</label>
    <input type="radio" name="gender" value="Female"> Female
    <input type="radio" name="gender" value="Male" checked> Male
  </div>
  <br>
  <button type="submit" class="btn-submit">Sign Up</button>
</form>
</body>
</html>
