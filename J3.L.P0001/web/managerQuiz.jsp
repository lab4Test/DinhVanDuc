<%-- 
    Document   : ManagerQuiz
    Created on : 14-05-2021, 00:53:56
    Author     : dinhd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/homePage.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>

    <body>
        <jsp:include page="navarBar.jsp"/>
        <div class=" div-header-1 ">
            <div class="sq-div-body">
                <div class="maq-div">
                    <p  class="col-lg-6">Number of questions: <span style="color:blue">${numbersQuestion}</span></p>
                </div>
                <div class="d-flex justify-content-center">
                    <div class="col-lg-6">
                        <p >Question</p>
                    </div>
                    <div class="col-lg-6">
                        <p >DateCreated</p>
                    </div>
                </div>
                <c:forEach items="${listQues}" var="o">
                    <div class="d-flex justify-content-center">
                        <div class="col-lg-6">
                            <p>${o.content}</p>
                        </div>
                        <div class="col-lg-6">
                            <p>${o.date}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>

