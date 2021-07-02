<%-- 
    Document   : DisplayQuiz
    Created on : 12-05-2021, 16:24:33
    Author     : dinhd
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/homePage.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src = "js/doQuiz.js"></script>
    </head>

    <body onload="countdown(${timeLeft})">
        <jsp:include page="navarBar.jsp"/>
        <div class="div-header-1 ">
            <div class="sq-div-body">
                <h4>Welcome <span style="color:blue">${sessionScope.account.userName}</span></h4>

                <br>
                <h4 style="float: right;padding-right: 15%;">
                    Time remaining: <span id="time" style="color:red"></span>
                </h4>
                <h3 id="timeStart" name="timeStart">${tottalTime}</h3>
                <div class="qa-div">
                    <h4>${question.content}</h4>
                    <form action="loadQuiz" method="get">
                        <ul>
                            <c:forEach items="${question.answer}" var="o">
                                <li><input type="radio" name="resultQues" value="${o.content}"> ${o.content}</li>
                                </c:forEach>
                        </ul>
                       <input type="hidden" id="endTime" name="endTime" value="2">
                       <c:if test="${lastQuestion eq false}">
                            <div class="containerBtn-Next">
                                <input type="submit" value="Next" id="nextBtn">
                            </div>
                        </c:if>
                        <c:if test="${lastQuestion eq true}">
                            <div class="containerBtn-Next">
                                <input type="submit" value="Finish" id="nextBtn">
                            </div>
                        </c:if>
                    </form>
                </div>

            </div>
        </div>
    </body>
</html>
