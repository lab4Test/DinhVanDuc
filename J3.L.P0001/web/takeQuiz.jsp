
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
        <script src="js/main2.js" type="text/javascript"></script>
    </head>

    <body>
        <jsp:include page="navarBar.jsp"/>
        <div class="div-header-1 ">
            <div class="tq-div-body">
                <h4 style="color: #647476">Welcome <span style="color:blue">${sessionScope.account.userName}</span></h4>
                <h4 style="color: red">${message}</h4>
                <h4 style="color: #647476">
                    Enter number of question
                </h4>      
                <form action="loadQuiz" method = "get">
                    <input onchange="checkNumbers()" id="numbers" type="text"  name="numbers" value="">
                    <br>
                    <div class="containerBtn">
                        <input class="start-btn" type="submit" value="Start" id="submitBtn">
                    </div>
                </form> 
            </div>
        </div>
    </body>

</html>
