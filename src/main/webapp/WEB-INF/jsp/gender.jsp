<%-- 
    Document   : prequiz
    Created on : Jun 18, 2015, 11:11:18 PM
    Author     : Rik Schaaf aka CC007 <http://coolcat007.nl/>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Science spin-offs: the technology we use</title>
        <link rel="stylesheet" type="text/css" href="${cp}/resources/css/style.css" />
        <script src="${cp}/resources/js/script.js"></script>
    </head>
    <body>
        <h1>Science spin-offs: the technology we use</h1>
        <h2>Are you male or female?</h2>
        <form name="answerform" id="answerform" class="answercontainer" method="get">
            <button class="answer" type="submit" name="gender" value="male">
                Male
            </button>
            <br/>
            <br/>
            <button class="answer" type="submit" name="gender" value="female" >
                Female
            </button>
            <input type="hidden" name="language" value="${language}" />
        </form>
    </body>
</html>
