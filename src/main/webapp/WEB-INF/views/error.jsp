<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8" />
    <title>Error</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes"/>
    <spring:url value="/resources/css/main.css" var="coreCss" />
    <spring:url value="/resources/favicon.ico" var="favicon" />
    <link rel="icon" type="image/png" href="${favicon}" />
    <link href="${coreCss}" rel="stylesheet" />
</head>

<body>
    <main class="error-page">
        <h1>Something went wrong...</h1>
        <a class="block" id="error-btn"
           href="${pageContext.request.contextPath}/">
            To the main page
        </a>
    </main>
</body>
</html>