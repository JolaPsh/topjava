<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 03.07.2018
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" type="text/css" href="meals_style.css">
<%--<jsp:include page="userform.html"></jsp:include>--%>

<body>
<section>
<h1><a href="index.html">Home</a></h1>
<h2>Meals</h2>
<a href="meals?action=add">Add </a>
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
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
    <tr bgcolor="${meal.exceed ? '#cc0000' : '#009933' }">
        <td>
                ${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}
        </td>
        <td>${meal.description}</td>
        <td>${meal.calories}</td>
        <td><a href="meals?action=edit&id=${meal.id}">Edit</a>
            &nbsp; / &nbsp;
            <a href="meals?action=delete&id=${meal.id}">Delete</a></td>
    </tr>
    </c:forEach>
    <tbody>
</table>
</section>
</body>
</html>
