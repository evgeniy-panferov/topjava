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
    <title>Title</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/style.css"%>
</style>

<table class="table-output">
    <tr>
        <th>Обновить пользователя</th>
    </tr>
    <tr>
        <th>
            <form class="back" method="post" action="mealsUpdate">
                <input class="input-inner" type="datetime-local" name="Date" placeholder="Введите дату"/>
                <br>
                <input class="input-inner" type="text" name="Description" placeholder="Введите прием пищи"/>
                <br>
                <input class="input-inner" type="text" name="Calories" placeholder="Сколько калорий съели"/>
                <br>
                <input class="input-inner" type="submit" name="Отправить">
            </form>
        </th>
    </tr>
</table>
</body>
</html>
