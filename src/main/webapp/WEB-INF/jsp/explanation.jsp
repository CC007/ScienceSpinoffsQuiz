<%-- 
    Document   : explanation
    Created on : May 25, 2015, 9:40:51 PM
    Author     : Rik Schaaf aka CC007 <http://coolcat007.nl/>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Explanation</title>
        <link rel="stylesheet" type="text/css" href="${cp}/resources/css/style.css" />
        <script src="${cp}/resources/js/script.js"></script>
    </head>
    <body>
        <h1>Science spin-offs: the technology we use</h1>
        <div class="halfcontainer">
            <div class="half ${hide}">
                <div class="questionText">${questionText}</div>
            </div>
            <div class="half">
                <img src="${cp}/resources/images/${imgName}" alt="Explanation image" class="image"> 
            </div>    
            <div class="clear"></div>
        </div>
        <div class="bordered">${explanation}</div>
        <br />
        <br />
        <form name="answerform" id="answerform" class="answercontainer" method="get">

            <input type="hidden" name="userid" value="${userid}" />
            <input type="hidden" name="random" value="${random}" />
            <input type="hidden" name="questionNr" value="${questionNr}" />
            <input type="hidden" name="language" value="${language}" />
            <button class="answer" type="submit" >
                ${nextQuestion}
            </button>
        </form>
    </body>
</html>
