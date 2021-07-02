<%-- 
    Document   : MakeQuiz
    Created on : 13-05-2021, 00:17:58
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
            <div class="sq-div-body">
                <form action="createQuiz" method="get">
                    <table class="mq-table">
                        <tr>
                            <td class="first-td">
                                <p>Question:</p>
                            </td>
                            <td><textarea name="question" id="firstArea"></textarea></td>
                        </tr>
                        <tr>
                            <td>
                                Option 1:
                            </td>
                            <td>
                                <textarea name="option1" class="option"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Option 2:
                            </td>
                            <td>
                                <textarea name="option2" class="option"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Option 3:
                            </td>
                            <td>
                                <textarea name="option3" class="option"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Option 4:
                            </td>
                            <td>
                                <textarea name="option4" class="option"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>Answer(s):</td>
                            <td>
                                <ul class="mq-ul">
                                    <div class="row">
                                        <li class="col-sm-12 col-md-6 col-lg-3"><input  type="radio" name="answer" value="1">
                                            <label for=""> Option 1</label></li>
                                        <li class="col-sm-12 col-md-6 col-lg-3"> <input type="radio" name="answer" value="2"> 
                                            <label for=""> Option 2</label></li>
                                        <li class="col-sm-12 col-md-6 col-lg-3"> <input type="radio" name="answer" value="3"> 
                                            <label for="">Option 3</label> </li>
                                        <li class="col-sm-12 col-md-6 col-lg-3"> <input type="radio" name="answer" value="4"> 
                                            <label for="">Option 4</label> </li></div>
                                </ul>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <div class="mq-button"><input type="submit" value="Save" id=""></div>
                            </td>
                        </tr>
                    </table>
                </form>

            </div>
        </div>
    </body>
</html>
