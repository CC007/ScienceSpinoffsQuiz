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
                        <b>4</b> participants of which:<br />
                        <b>50/50</b>% male/female<br />
                        <b>50/50</b>% below/above 30<br />
                        <b>38</b> questions answered<br />
                        <b>17</b> answered correctly<br />
                    </div>
                </div>

                <!-- Gender graph -->
                <div class="graph">
                    <h2>Gender</h2>
                    <div class="flexbox hor graphbars">
                        <div>
                            <div style="height: calc(100% - 66%)">

                            </div>
                            <div class="bar" style="height: 66%">
                                66%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - 25%)">

                            </div>
                            <div class="bar" style="height: 25%">
                                25%
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
                            <div style="height: calc(100% - 66%)">

                            </div>
                            <div class="bar" style="height: 66%">
                                66%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - 25%)">

                            </div>
                            <div class="bar" style="height: 25%">
                                25%
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
                            <div style="height: calc(100% - 66%)">

                            </div>
                            <div class="bar" style="height: 66%">
                                66%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - 25%)">

                            </div>
                            <div class="bar" style="height: 25%">
                                25%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - 66%)">

                            </div>
                            <div class="bar" style="height: 66%">
                                66%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - 25%)">

                            </div>
                            <div class="bar" style="height: 25%">
                                25%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - 66%)">

                            </div>
                            <div class="bar" style="height: 66%">
                                66%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - 25%)">

                            </div>
                            <div class="bar" style="height: 25%">
                                25%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - 66%)">

                            </div>
                            <div class="bar" style="height: 66%">
                                66%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - 25%)">

                            </div>
                            <div class="bar" style="height: 25%">
                                25%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - 66%)">

                            </div>
                            <div class="bar" style="height: 66%">
                                66%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - 25%)">

                            </div>
                            <div class="bar" style="height: 25%">
                                25%
                            </div>
                        </div>
                        <div>
                            <div style="height: calc(100% - 66%)">

                            </div>
                            <div class="bar" style="height: 66%">
                                66%
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
