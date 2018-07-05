<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04.07.2018
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" type="text/css" href="meals_form_style.css">
<body>
<h1><a href="index.html">Home</a></h1>
<jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
<form method="post" action="meals">
    <table border="1" cellpadding="5">
        <input type="hidden" name="id" value="${meal.id}"/> </br>
        <tr>
            <th> Date :</th>
            <td>
                <input type="datetime-local" value="${meal.dateTime}" size="40" height="15" maxlength="20"
                       name="dateTime" required/>
            </td>
        </tr>
        <tr>
            <th>Description:</th>
            <td>
                <input type="text" value="${meal.description}" size="40" height="15" maxlength="40" name="description"
                       required/>
            </td>
        </tr>
        <tr>
            <th>Calories:</th>
            <td>
                <input type="number" value="${meal.calories}" size="40" height="15" maxlength="4" name="calories"
                       required/>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <button type="submit">Save</button>
                <button type="button" name="back" onclick="window.history.back()">Back</button>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
