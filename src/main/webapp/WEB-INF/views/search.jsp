<%@ page import="ru.sfu.entity.CoffeeMachine" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8" />
    <title>Search</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes"/>
    <spring:url value="/resources/css/main.css" var="coreCss" />
    <spring:url value="/resources/favicon.ico" var="favicon" />
    <link rel="icon" type="image/png" href="${favicon}" />
    <link rel="stylesheet" type="text/css" href="${coreCss}" />
</head>

<body>
    <header>
        <h1>Search by year</h1>

        <form method="get">
            <div class="wide-field">
                <label for="year">Minimal year</label>
                <input name="year" id="year" type="number" inputmode="numeric" />
            </div>
            <div class="form-control">
                <button type="submit" class="block form-btn" id="submit">
                    Search
                </button>
                <a href="${pageContext.request.contextPath}/"
                   class="block form-btn" id="back">
                    Back
                </a>
            </div>
        </form>
    </header>

    <main>
        <%
            ArrayList<CoffeeMachine> machines = null;
            try {
                machines = (ArrayList<CoffeeMachine>) request.getAttribute("machines");
            } catch (Exception ignored) {}

            if (machines != null && machines.isEmpty()) {
        %>
        Required items not found
        <%
            }
            if (machines != null)
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