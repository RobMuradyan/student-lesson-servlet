<%@ page import="com.example.studentlessonservlet.model.Lesson" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 111
  Date: 15.01.2024
  Time: 2:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");%>
<head>
    <title>addstudent</title>
</head>
<body>
add student <br>
<form method="post" action="/addstudent" enctype="multipart/form-data">
    Student name:<input type="text" name="name" placeholder="name">
    Student surname:<input type="text" name="surname" placeholder="surname">
    Student email:<input type="text" name="email" placeholder="email">
    Student age:<input type="number" name="age" placeholder="age">

    <select name="lesson_id">
        <%
            for (Lesson lesson : lessons) { %>
        <option value="<%=lesson.getId()%>"><%=lesson.getName()%>
        </option>
        <% }%>
    </select> <br>
    <input type="file" name="picture">
    <input type="submit" name="add">


</form>
</body>
</html>
