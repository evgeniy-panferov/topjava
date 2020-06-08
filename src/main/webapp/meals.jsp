<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%--Created by IntelliJ IDEA.--%>
<%--User: Mars--%>
<%--Date: 06.06.2020--%>
<%--Time: 18:55--%>
<%--To change this template use File | Settings | File Templates.--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/style.css"%>
</style>

<table>
    <th>Дата</th>
    <th>Прием пищи</th>
    <th>Калории</th>
    <c:forEach items="${mealTo}" var="mealTo">
    <tr id= ${mealTo.excess ? "tr-true" : "tr-false"}>
            <javatime:format value="${mealTo.dateTime}" pattern="yyyy-MM-dd HH:mm" var="parsedDate"/>
        <th>${parsedDate} </th>
        <th> ${mealTo.description}</th>
        <th> ${mealTo.calories}</th>
    <tr>
        </c:forEach>
</table>

</body>
</html>
