<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>

<html>
<head>
    <title>Meal list</title>
    <link rel="stylesheet" type="text/css" href="meals_style.css">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<form method="post" action="meals?action=filter">
    <div class="header">
        <h4>My meal</h4>
        <p> Filter by date / time</p>
    </div>
    <div class="data-inputs">
        <div class="col-1">
            <dl>
                <dt>From date:</dt>
                <dd><input type="date" value="${param.startDate}" name="start-date"></dd>
            </dl>
            <dl>
                <dt>To date:</dt>
                <dd><input type="date" value="${param.endDate}" name="end-date"/></dd>
            </dl>
        </div>
        <div class="col-2">
            <dl>
                <dt>From time:</dt>
                <dd><input type="time" value="${param.startTime}" name="start-time"/></dd>
            </dl>
            <dl>
                <dt>To time:</dt>
                <dd><input type="time" value="${param.endTime}" name="end-time"/></dd>
            </dl>
        </div>
    </div>
    <button type="submit">Filter</button>
</form>

<h2>Meals</h2>
<a href="meals?action=create">Add Meal</a>
<hr/>
<table>
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Actions</th>
    </tr>
    </thead>
    <c:forEach items="${meals}" var="meal">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
        <tr bgcolor="${meal.exceed ? '#cc0000' : '#009933'}">
            <td>
                    ${fn:formatDateTime(meal.dateTime)}
            </td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=update&id=${meal.id}">Update</a>
                &nbsp; / &nbsp;
                <a href="meals?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</section>
</body>
</html>