<%-- 
    Document   : question
    Created on : May 25, 2015, 9:09:50 PM
    Author     : Rik Schaaf aka CC007 <http://coolcat007.nl/>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz</title>
        <link rel="stylesheet" type="text/css" href="${cp}/resources/css/style.css" />
        <script src="${cp}/resources/js/script.js"></script>
    </head>
    <body>
        <div><h1>Science spin-offs: the technology we use</h1></div>
        <div class="bordered">
            ${question}
        </div>
        <br/>
        <br/>
        <form name="answerform" id="answerform" class="answercontainer" method="get">
            <button class="answer" type="submit" name="answer" value="A">
                ${answerA}
            </button>
            <br/>
            <br/>
            <button class="answer" type="submit" name="answer" value="B" >
                ${answerB}
            </button>
            <br/>
            <br/>
            <button class="answer" type="submit" name="answer" value="C" >
                ${answerC}
            </button>
            <input type="hidden" name="userid" value="${userid}" />
            <input type="hidden" name="random" value="${random}" />
            <input type="hidden" name="questionNr" value="${questionNr}" />
            <input type="hidden" name="language" value="${language}" />
        </form>

    </body>
</html>
