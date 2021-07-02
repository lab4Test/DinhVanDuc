<%-- 
    Document   : NavarBar
    Created on : 11-05-2021, 23:56:48
    Author     : dinhd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="div-header">
</div>
<div class="div-header-2 d-flex justify-content-center">
    <ul class="ul-header-1 row ">
        <c:if test="${sessionScope.account==null}">
            <li class="col-xs-12 col-md-3">
                <a href="homeControl">Home</a>
            </li>
           
        </c:if>
        <c:if test="${sessionScope.account!=null&&sessionScope.account.roleUser==1}">
            <li class="col-xs-12 col-md-4 col-lg-1">
                <a href="homeControl">Home</a>
            </li>
            <li class="col-xs-12 col-md-4 col-lg-3">
                
                <c:if test="${sessionScope.doingQuiz eq null}">
                    <a href="takeQuiz">Take Quiz</a>
                </c:if>
                <c:if test="${sessionScope.doingQuiz ==1}">
                     <a href="#">Take Quiz</a>
                </c:if>
            </li>
            <li class="col-xs-12 col-md-4 col-lg-3">
                <c:if test="${sessionScope.doingQuiz eq null}">
                    <a href="makeQuiz.jsp">Make Quiz</a>
                </c:if>
                <c:if test="${sessionScope.doingQuiz ==1}">
                     <a href="#">Make Quiz</a>
                </c:if>
            </li>
            <li class="col-xs-12 col-md-4 col-lg-3">
                
                <c:if test="${sessionScope.doingQuiz eq null}">
                    <a href="viewQuiz">Manage Quiz</a>
                </c:if>
                <c:if test="${sessionScope.doingQuiz == 1}">
                     <a href="#">Manage Quiz</a>
                </c:if>
            </li>
            <li class="col-xs-12 col-md-4 col-lg-2">
                <a href="Logout">Log out</a>
            </li>
        </c:if>
        <c:if test="${sessionScope.account!=null&&sessionScope.account.roleUser==0}">
            <li class="col-xs-12 col-md-4">
                <a href="homeControl">Home</a>
            </li>
            <li class="col-xs-12 col-md-4">
                <a href="takeQuiz">Take Quiz</a>
            </li>
            <li class="col-xs-12 col-md-4">
                <a href="Logout">Log out</a>
            </li>
        </c:if>
    </ul>
</div>
