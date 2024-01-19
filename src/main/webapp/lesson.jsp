<%@ page import="com.example.studentlessonservlet.model.Lesson" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 111
  Date: 15.01.2024
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>lesson</title>
</head>

<body>
<%List<Lesson> lessonList= (List<Lesson>) request.getAttribute("lessons1");%>
<h1>all lessons</h1>
<a href="addlesson.jsp">add lesson</a>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Duration</th>
        <th>Lecturername</th>
        <th>price</th>
        <th>User added</th>
    </tr>
    <%
        if (!lessonList.isEmpty()){
            for (Lesson lesson : lessonList) {
    %>
    <tr>
        <td><%=lesson.getId()%></td>
        <td> <%=lesson.getName()%></td>
        <td><%=lesson.getDuration()%></td>
        <td><%=lesson.getLecturername()%></td>
        <td><%=lesson.getPrice()%></td>
        <td><%if (lesson.getUser()!=null){%>
        <%=lesson.getUser().getName()+" "+lesson.getUser().getSurname()%>
        <%}%></td>
        <td><a href="/deleteLesson?id=<%= lesson.getId() %>">delete</a>
    </tr>
    <%}}%>
</table>

</body>
</html>
