<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<fmt:setBundle basename="messages.app"/>
<header><a href="${pageContext.request.contextPath}/"><spring:message code="app.home"/></a>&nbsp;|&nbsp;<a
        href="meals"><spring:message code="app.title"/></a>
</header>