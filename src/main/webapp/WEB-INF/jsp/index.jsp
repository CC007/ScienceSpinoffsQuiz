<%-- 
    Document   : explanation
    Created on : May 25, 2015, 9:41:41 PM
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
        ${quizredirect}
        <link rel="stylesheet" type="text/css" href="${cp}/resources/css/style.css" />
        <script src="${cp}/resources/js/script.js"></script>
    </head>
    <body>
        <h1>Science spin-offs: the technology we use</h1>
        
        <form name="answerform" id="answerform" class="answercontainer" method="get">
            <button class="answer" type="submit" name="language" value="English">
                English
            </button>
            <br/>
            <br/>
            <button class="answer" type="submit" name="language" value="Nederlands" >
                Nederlands
            </button>
        </form>
    </body>
</html>
