<%@ page import="java.util.List" %>
<%@ page import="com.example.studentlessonservlet.model.Student" %><%--
  Created by IntelliJ IDEA.
  User: 111
  Date: 13.01.2024
  Time: 23:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>students</title>
</head>
<body>
<%List<Student> students= (List<Student>) request.getAttribute("students");%>
<h1>all students</h1>
<a href="/addstudent">add student</a>
<table>
    <tr>
        <th>ID</th>
        <th>Picture</th>
        <th>Name</th>
        <th>Surname</th>
        <th>email</th>
        <th>age</th>
        <th>lesson_id</th>
        <th>User added</th>
    </tr>
    <%
        if (students!=null&&!students.isEmpty()){
            for (Student student : students) {
    %>
    <tr>
        <td><%=student.getId()%></td>
        <td><%if (student.getPicName()!=null){%>
            <img src="/downloadImage?imageName=<%=student.getPicName()%>">
            <%}else {%>
            <span>NO Picture</span>
            <%}%>

        </td>
        <td> <%=student.getName()%></td>
        <td><%=student.getSurname()%></td>
        <td><%=student.getEmail()%></td>
        <td><%=student.getAge()%></td>
        <td><%=student.getLesson().getName()%></td>
        <td><%if (student.getUser()!=null){%>
            <%=student.getUser().getName()+" "+student.getUser().getSurname()%>
            <%}%></td>
        <td><a href="/deleteStudent?id=<%= student.getId() %>">delete</a></td>
    </tr>
    <%}}%>
</table>
</body>
</html>
