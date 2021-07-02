/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.QuestionDAO;
import entity.AccountEntity;
import entity.AnswerEntity;
import entity.QuestionEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
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
@WebServlet(name = "CreateQuiz", urlPatterns = {"/createQuiz"})
public class CreateQuizController extends HttpServlet {

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
        String questionContent = request.getParameter("question");
        if (questionContent.equals("")) {
            response.sendRedirect("makeQuiz.jsp");
            return;
        }
        //create new vectre answer enity to save value input
        Vector<AnswerEntity> listAnswer = new Vector<AnswerEntity>();
        String option;
        for (int i = 0; i < 4; i++) {

            listAnswer.add(new AnswerEntity());

            //get value of parameter to save into content of Answer
            int postionOption = i + 1;

            //get the values by name in turn
            option = request.getParameter("option" + postionOption);
            listAnswer.get(i).setContent(option);
            //save the default value if the answers are wrong
            listAnswer.get(i).setValue(false);
            if (option.equals("")) {
                response.sendRedirect("makeQuiz.jsp");
                return;
            }
        }

        //create new question entity
        QuestionEntity question = new QuestionEntity();
        question.setContent(questionContent);
        question.setAnswer(listAnswer);

        //get all value from input checkbox to array
        String[] answer = request.getParameterValues("answer");
        int flag = 1;
        //save value be got to know what answer is true or false
        for (int i = 0; i < 4; i++) {
            for (String s : answer) {
                if ((i + 1 + "").equals(s)) {
                    listAnswer.get(i).setValue(true);
                    flag = 0;
                }
            }
        }
        if (flag == 1) {
            response.sendRedirect("makeQuiz.jsp");
            return;
        }
        HttpSession session = request.getSession();
        //get account from session to get id account
        AccountEntity account = (AccountEntity) session.getAttribute("account");

        //call function to push data to database
        new QuestionDAO().pushQuestionToDB(question, account.getId());

        response.sendRedirect("makeQuiz.jsp");
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
