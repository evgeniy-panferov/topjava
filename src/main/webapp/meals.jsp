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
    <th>Дата</th>
    <th>Прием пищи</th>
    <th>Калории</th>
    <th>id</th>
    <th>Обновить</th>
    <th>Удалить</th>
    <c:forEach items="${mealTo}" var="mealTo">
        <tr id= ${mealTo.excess ? "tr-true" : "tr-false"}>
            <javatime:format value="${mealTo.dateTime}" pattern="yyyy-MM-dd HH:mm" var="parsedDate"/>
            <th>${parsedDate}</th>
            <th> ${mealTo.description}</th>
            <th> ${mealTo.calories}</th>
            <th> ${mealTo.id}</th>
            <th>
                <form class="back" method="post" action="mealsUpdate">
                    <input class="input-inner" type="datetime-local" name="Date" placeholder="Введите дату"/>
                    <br>
                    <input class="input-inner" type="text" name="Description" placeholder="Введите прием пищи"/>
                    <br>
                    <input class="input-inner" type="text" name="Calories" placeholder="Сколько калорий съели"/>

                    <button type="submit" name="Id" value="${mealTo.id}">Обновить</button>
                </form>
            </th>
            <th>
                <form class="back" method="post" action="mealsRemove">
                    <button type="submit" name="Id" value="${mealTo.id}">Удалить</button>
                </form>
            </th>
        </tr>
    </c:forEach>

</table>
<br>
<table class="table-output">
    <tr>
        <th>Добавить пользователя</th>
    </tr>
    <tr>
        <th>
            <form class="back" method="post" action="mealsAdd">
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
