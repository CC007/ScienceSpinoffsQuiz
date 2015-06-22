<%-- 
    Document   : statistics
    Created on : May 25, 2015, 9:41:01 PM
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
        <meta http-equiv="refresh" content="10;url=/ScienceSpinoffsQuiz/explanation" />
        <link rel="stylesheet" type="text/css" href="${cp}/resources/css/statisticsstyle.css" />
        <script src="${cp}/resources/js/script.js"></script>
    </head>
    <body>
        <h1>Science spin-offs: the technology we use</h1>
        <div class="flexbox vert">
            <div class="flexbox hor">
                <!-- Statistics -->
                <div id="globalstatistics">
                    <div>
                        <b>${participantsCount}</b> participants of which:<br />
                        <b>${malePercentage}/${femalePercentage}</b>% male/female<br />
                        <b>${youngerPercentage}/${olderPercentage}</b>% below/above 30<br />
                        <b>${answeredCount}</b> questions answered<br />
                        <b>${rightlyAnsweredCount}</b> answered correctly<br />
                    </div>
                </div>

                <!-- Gender graph -->
                <div class="graph">
                    <h2>Gender</h2>
                    <div class="flexbox hor graphbars">
                        <div>
                            <div style="height: calc(100% - ${gender1}%)">

                            </div>
                            <div class="bar" style="height: ${gender1}%">
                                ${gender1}%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - ${gender2}%)">

                            </div>
                            <div class="bar" style="height: ${gender2}%">
                                ${gender2}%
                            </div>
                        </div>
                    </div>
                    <div class="flexbox hor">
                        <div class="barname">
                            male
                        </div>
                        <div class="barname">
                            female
                        </div>
                    </div>
                </div>

                <!-- agegroup graph -->
                <div class="graph">
                    <h2>Age group</h2>
                    <div class="flexbox hor graphbars">
                        <div>
                            <div style="height: calc(100% - ${ageGroup1}%)">

                            </div>
                            <div class="bar" style="height: ${ageGroup1}%">
                                ${ageGroup1}%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - ${ageGroup2}%)">

                            </div>
                            <div class="bar" style="height: ${ageGroup2}%">
                                ${ageGroup2}%
                            </div>
                        </div>
                    </div>
                    <div class="flexbox hor">
                        <div class="barname">
                            &lt;30
                        </div>
                        <div class="barname">
                            &gt;30
                        </div>
                    </div>
                </div>

            </div>
            <div>

                <!-- questions graph -->
                <div class="graph">
                    <h2>Score per question</h2>
                    <div class="flexbox hor graphbars">
                        <div>
                            <div style="height: calc(100% - ${question1}%)">

                            </div>
                            <div class="bar" style="height: ${question1}%">
                                ${question1}%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - ${question2}%)">

                            </div>
                            <div class="bar" style="height: ${question2}%">
                                ${question2}%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - ${question3}%)">

                            </div>
                            <div class="bar" style="height: ${question3}%">
                                ${question3}%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - ${question4}%)">

                            </div>
                            <div class="bar" style="height: ${question4}%">
                                ${question4}%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - ${question5}%)">

                            </div>
                            <div class="bar" style="height: ${question5}%">
                                ${question5}%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - ${question6}%)">

                            </div>
                            <div class="bar" style="height: ${question6}%">
                                ${question6}%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - ${question7}%)">

                            </div>
                            <div class="bar" style="height: ${question7}%">
                                ${question7}%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - ${question8}%)">

                            </div>
                            <div class="bar" style="height: ${question8}%">
                                ${question8}%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - ${question9}%)">

                            </div>
                            <div class="bar" style="height: ${question9}%">
                                ${question9}%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - ${question10}%)">

                            </div>
                            <div class="bar" style="height: ${question10}%">
                                ${question10}%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - ${question11}%)">

                            </div>
                            <div class="bar" style="height: ${question11}%">
                                ${question11}%
                            </div>
                        </div>
                    </div>
                    <div class="flexbox hor">
                        <div class="barname">
                            1
                        </div>
                        <div class="barname">
                            2
                        </div>
                        <div class="barname">
                            3
                        </div>
                        <div class="barname">
                            4
                        </div>
                        <div class="barname">
                            5
                        </div>
                        <div class="barname">
                            6
                        </div>
                        <div class="barname">
                            7
                        </div>
                        <div class="barname">
                            8
                        </div>
                        <div class="barname">
                            9
                        </div>
                        <div class="barname">
                            10
                        </div>
                        <div class="barname">
                            11
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>
