<%@ page import="ru.sfu.entity.CMValidator" %>
<%@ page import="ru.sfu.entity.CoffeeMachine" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8" />
    <title>Editing</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes"/>
    <spring:url value="/resources/css/main.css" var="coreCss" />
    <spring:url value="/resources/favicon.ico" var="favicon" />
    <link rel="icon" type="image/png" href="${favicon}" />
    <link rel="stylesheet" type="text/css" href="${coreCss}" />
</head>

<body>
    <header>
        <h1>Editing a coffee machine</h1>
    </header>

    <main>
        <%
            CoffeeMachine m = (CoffeeMachine) request.getAttribute("m");
            CMValidator v = null;
            try {
                v = (CMValidator) request.getAttribute("v");
            } catch (Exception ignored) {}
        %>
        <form method="post">
            <div class="wide-field">
                <label for="id">ID</label>
                <input name="id" id="id" type="number" value="<%=m.getId()%>" readonly/>
            </div>

            <div class="wide-field">
                <label for="name">Name</label>
                <input name="name" id="name" value="<%=m.getName()%>"/>
                <% if (v != null && v.invalidName()) { %>
                    <span class="error-field">ERROR: a non-empty string is required</span>
                <% } %>
            </div>

            <div class="wide-field">
                <label for="country">Country of manufacture</label>
                <input name="country" id="country" value="<%=m.getCountry()%>"/>
            </div>

            <div class="wide-field">
                <label for="year">Year of manufacture</label>
                <input name="year" id="year" type="number"
                       inputmode="numeric" value="<%=m.getYear()%>"/>
                <% if (v != null && v.invalidYear()) { %>
                <span class="error-field">ERROR: a number from 1800 to 2023 is required</span>
                <% } %>
            </div>

            <div class="wide-field">
                <label for="reservoir">Volume of reservoir (in grams)</label>
                <input name="reservoir" id="reservoir" type="number"
                       inputmode="numeric" value="<%=m.getReservoir()%>"/>
            </div>

            <div class="wide-field">
                <label for="description">Description</label>
                <textarea name="description" id="description" rows="3"
                ><%=m.getDescription()%></textarea>
            </div>


            <div class="form-control">
                <button type="submit" class="block form-btn" id="submit">
                    Save
                </button>
                <a href="${pageContext.request.contextPath}/"
                   class="block form-btn" id="back">
                    Back
                </a>
            </div>
        </form>
    </main>
</body>
</html>