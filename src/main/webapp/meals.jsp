<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 03.07.2018
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" type="text/css" href="meals_style.css">
<%--<jsp:include page="userform.html"></jsp:include>--%>

<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<table>
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${meals}" var="meal">
    <tr bgcolor="${meal.exceed ? '#cc0000' : '#009933' }">
        <td>
                ${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}
        </td>
        <td><c:out value="${meal.description}"/></td>
        <td><c:out value="${meal.calories}"/></td>
        <td><a href="/meals_form.html?id=<c:out value='${meal.id}' />">Edit</a>
            &nbsp; / &nbsp;
            <a href="/meals_form.html?id=<c:out value='${meal.id}' />">Delete</a></td>
    </tr>
    </c:forEach>
    <tbody>
</table>

<a href="meals?action=add">Add Meal</a>
<%--<button class="button">Add</button>--%>
<input type="button" value="Add"/>
</body>
</html>
</body>
</html>