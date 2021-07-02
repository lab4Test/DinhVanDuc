/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "scoreController", urlPatterns = {"/scoreController"})
public class ScoreController extends HttpServlet {

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
         session.removeAttribute("doingQuiz");
        //get all value saved on session
        int score = (int) session.getAttribute("score");
        int numbersQuestion = (int) session.getAttribute("numbers");
        
        //calculate score
        float result = (float) score / numbersQuestion;
        //round number
        String latResult = String.format("%.2f", result * 10);
        String latResultPercent = String.format("%.2f", result * 100);
        
        //If 00 is after the comma, it will be deleted. Ex: 100.00
        if(latResult.endsWith(",00")){
            latResult = latResult.replace(",00", "");
        }
        if(latResultPercent.endsWith(",00")){
            latResultPercent = latResultPercent.replace(",00", "");
        }
        
        //set all attribute after calculate to display
        request.setAttribute("latResult", latResult);
        request.setAttribute("latResultPercent", latResultPercent);
        
        String message = result >= 0.5 ? "Passed" : "Not Pass";
        request.setAttribute("messageResult", message);
        request.getRequestDispatcher("displayScore.jsp").forward(request, response);
   
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