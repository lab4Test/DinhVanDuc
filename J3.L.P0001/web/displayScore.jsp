<%-- 
    Document   : DisplayScore
    Created on : 14-05-2021, 17:39:30
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
    </head>

    <body>
        <jsp:include page="navarBar.jsp"/>
        <div class="div-header-1 ">
            <div class="tq-div-body">
                <h4 style="color:#647476">Your score: <span style="color:blue">
                        ${latResult} (${latResultPercent})% - ${messageResult}
                    </span></h4>
                <br>
                <h4 style="color:#647476">
                    Take another test <button><a href="takeQuiz?reset=1">Start</a></button>
                </h4>
            </div>
        </div>
    </body>

</html>