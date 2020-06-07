<%@ page import="ru.javawebinar.topjava.model.MealTo" %>
<%@ page import="java.util.List" %>
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
<style type="text/css">
    table {
        border: 2px solid #0c0c0c;
        width: 100%;
        border-spacing: 0px;
        border-collapse: collapse;
    }

    tr {
        background: goldenrod;
    }

    #tr-true {
        background: antiquewhite;
        color: brown;
    }

    #tr-false {
        background: antiquewhite;
        color: green;
    }

    th {
        font-size: 24px;
        width: 33%;
        border:1px solid #0c0c0c;
    }
</style>

<table>

    <th>Дата</th>
    <th>Прием пищи</th>
    <th>Калории</th>
    <c:forEach items="${mealTo}" var="mealTo">
    <c:if test="${mealTo.excess eq true}">
    <tr id="tr-true">
        </c:if>
        <c:if test="${mealTo.excess eq false}">
    <tr id="tr-false">
        </c:if>
            <javatime:format value="${mealTo.dateTime}" pattern="yyyy-MM-dd HH:mm" var="parsedDate"/>
        <th>${parsedDate} </th>
        <th> ${mealTo.description}</th>
        <th> ${mealTo.calories}</th>
    <tr>
        </c:forEach>
</table>

</body>
</html>
