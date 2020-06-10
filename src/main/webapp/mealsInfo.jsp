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
    <title>MealsCrud</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/style.css"%>
</style>
<table class="table-output">
    <tr>
        <th colspan="3"> Добавлено</th>
    </tr>
    <tr id="tr-true">
        <th>${Date}</th>
        <th>${Description}</th>
        <th>${Calories}</th>
    </tr>
    <tr>
        <th colspan="3">
    <form class="back" action="meals">
        <button class="back" type="submit">Назад</button>
    </form>
        </th>
    </tr>
</table>
</body>
</html>
