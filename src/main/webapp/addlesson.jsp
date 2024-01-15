<%--
  Created by IntelliJ IDEA.
  User: 111
  Date: 15.01.2024
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addlesson</title>
</head>
<body>

add lesson <br>
<form method="post" action="/addLesson">
    Lesson name:<input type="text" name="name" placeholder="name">
    Lesson duration:<input type="time" name="duration" placeholder="duration">
    Lesson lecturername:<input type="text" name="lecturername" placeholder="lecturername">
    Lesson price:<input type="number" name="price" placeholder="price">
    <input type="submit" name="add">


</form>

</body>
</html>
