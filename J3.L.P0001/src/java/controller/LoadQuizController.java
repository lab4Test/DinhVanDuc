/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.QuestionDAO;
import entity.QuestionEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dinhd
 */
@WebServlet(name = "LoadQuiz", urlPatterns = {"/loadQuiz"})
public class LoadQuizController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        QuestionDAO questionDAO = new QuestionDAO();

        int totalQuestionExist = questionDAO.getTotalQuestion();

        //get next question
        //postion question help get next question by offset query sql
        int positionNextQuestion = 0;

        //if user take first question postion quesion auto equal 0
        if (session.getAttribute("positionQuestion") == null) {
            session.setAttribute("positionQuestion", 0);
        } else {
            //from 2nd question, position will be got from session and increase 1
            positionNextQuestion = (int) session.getAttribute("positionQuestion");
            positionNextQuestion += 1;
            //save back to session
            session.setAttribute("positionQuestion", positionNextQuestion);
        }

        //flag use to check user finshed last question or time up
        int flag = 0;
        boolean isLastQuestion = false;

        //if session numbers of question doesnt exist, program will create it
        //to check when user do last question
        int numbersQuestion;
        if (session.getAttribute("numbers") == null) {
            numbersQuestion = Integer.parseInt(request.getParameter("numbers"));
            session.setAttribute("numbers", numbersQuestion);

            //if user do last question, flag mark 1
            //this case when user just do one question
            if (numbersQuestion == positionNextQuestion) {
                flag = 1;
            }

            if (numbersQuestion == positionNextQuestion + 1) {
                isLastQuestion = true;
            }
        } else {
            //if user do last question, flag mark 1
            //this case when user just do many questions
            numbersQuestion = (int) session.getAttribute("numbers");
            if (numbersQuestion == positionNextQuestion) {
                flag = 1;
            }
            if (numbersQuestion == positionNextQuestion + 1) {
                isLastQuestion = true;
            }
        }

        String checkTimeUp = request.getParameter("endTime");
        if (checkTimeUp != null && checkTimeUp.contains("1")) {
            flag = 1;
        }

        if (numbersQuestion > totalQuestionExist) {
            session.removeAttribute("numbers");
            session.removeAttribute("positionQuestion");
            request.setAttribute("messageQuiz", "Please enter numbers of question less than " + totalQuestionExist);
            request.getRequestDispatcher("takeQuiz").forward(request, response);
            return;
        }
        //time control
        long timeLeft;
        if (session.getAttribute("endTime") == null) {
            //get time start if user do first question
            long startTime = new Date().getTime();
            //60 * 1000 ms = 60s * total of question
            //calculate time end 
            long endTime = startTime + (10 * 1000 * numbersQuestion);
            timeLeft = (10 * 1000 * numbersQuestion);
            session.setAttribute("endTime", endTime);
        } else {
            long timeNow = new Date().getTime();
            //calculate remaining time
            timeLeft = (long) session.getAttribute("endTime") - timeNow;
        }

        session.setAttribute("timeLeft", timeLeft);

        //get answer of user
        String resultContent = request.getParameter("resultQues");

        //get all content and answer of quesion
        QuestionEntity question = questionDAO.getQuestion(positionNextQuestion);

        //check answer is right or wrong
        boolean checkResult = false;
        if (session.getAttribute("positionQuestion") != null) {
            if ((int) session.getAttribute("positionQuestion") != 0) {
                //postionNextQuestion is id of this question in database
                checkResult = questionDAO.checkResult(positionNextQuestion, resultContent);
            }
        }

        //create a session to save score if it does not exist
        if (session.getAttribute("score") == null) {
            session.setAttribute("score", 0);
        } else {
            //get score saved by session
            int score = (int) session.getAttribute("score");

            //if checkResult is true, score increase
            if (checkResult) {
                score++;
            }

            //save new value of score to session
            session.setAttribute("score", score);
        }
        session.setAttribute("doingQuiz", 1);
        //flag == 1 means user had been finished test and will be seen user score
        if (flag == 1) {
            request.getRequestDispatcher("scoreController").forward(request, response);
            return;
        }
        request.setAttribute("question", question);
        if (isLastQuestion) {
            request.setAttribute("lastQuestion", true);
        } else {
            request.setAttribute("lastQuestion", false);
        }
        //otherwise user will do next question
        request.getRequestDispatcher("displayQuiz.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
