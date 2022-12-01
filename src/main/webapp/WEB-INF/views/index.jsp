<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="ru.sfu.entity.CoffeeMachine" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8" />
    <title>Coffee machine database</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes"/>
    <spring:url value="/resources/css/main.css" var="coreCss" />
    <spring:url value="/resources/favicon.ico" var="favicon" />
    <link rel="icon" type="image/png" href="${favicon}" />
    <link href="${coreCss}" rel="stylesheet" />
</head>

<body>

    <header>
        <h1>Welcome to the coffee machine database</h1>
        <div class="container">
            <a class="block" id="add"
               href="${pageContext.request.contextPath}/add">
                Add a coffee machine
            </a>
            <a class="block" id="search"
               href="${pageContext.request.contextPath}/search">
                Search by year
            </a>
        </div>
    </header>

    <main>
        <%
            ArrayList<CoffeeMachine> machines = (ArrayList<CoffeeMachine>) request.getAttribute("machines");
            for (CoffeeMachine machine: machines) {
        %>
        <div class="item block">
            <p class="description"><%=machine.toString()%></p>
            <p class="container">
                <a class="edit link-button"
                   href="${pageContext.request.contextPath}/edit/<%=machine.getId()%>">
                    Edit
                </a>
                <% if (machine.getBought()) { %>
                <span class="bought">Bought</span>
                <% } else { %>
                <a class="buy-link link-button"
                   href="${pageContext.request.contextPath}/buy/<%=machine.getId()%>">
                    Buy
                </a>
                <% } %>
                <a class="del link-button"
                   href="${pageContext.request.contextPath}/del/<%=machine.getId()%>">
                    Delete
                </a>
            </p>
        </div>
        <% } %>
    </main>
</body>
</html>