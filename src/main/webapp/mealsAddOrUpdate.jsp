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

<table class="table-output">
    <tr>
            <form class="back" method="post" action="meals" >
                <input class="input-inner" type="hidden" value="add" name="add"/>
                <input class="input-inner" type="datetime-local" name="Date" placeholder="Введите дату"/>
                <br>
                <input class="input-inner" type="text" name="Description" placeholder="Введите прием пищи"/>
                <br>
                <input class="input-inner" type="text" name="Calories" placeholder="Сколько калорий съели"/>
                <br>
                <input class="input-inner" type="submit" >
            </form>
        </th>
    </tr>
</table>
</body>
</html>
