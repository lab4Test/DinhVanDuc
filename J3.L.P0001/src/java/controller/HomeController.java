/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.AccountEntity;
import java.io.IOException;
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
@WebServlet(name = "homeControl", urlPatterns = {"/homeControl"})
public class HomeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

   
    //remove all session attribute had been create frorm servlet loadQuiz
    public void removeAtrributeSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("positionQuestion");
        session.removeAttribute("message");
        session.removeAttribute("numbers");
        session.removeAttribute("score");
        session.removeAttribute("timeLeft");
        session.removeAttribute("endTime");
        session.removeAttribute("endTest");
         session.removeAttribute("doingQuiz");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        removeAtrributeSession(request);
        String s="";
        if(request.getParameter("fail")!=null){
            s="Login Fail!";
        }
        //this string use to display login from
        String htmls = " <div class=\"div-body div-login-res\">\n"
                + "            <h4>Login Form</h4><br><h4 style=\"Color: Red\">"+s+"<h4> <br>\n"
                + "            <form action=\"login\" method=\"post\">\n"
                + "                <table>\n"
                + "                    <tr>\n"
                + "                        <td>\n"
                + "                            <label for=\"uname\">User Name:</label>\n"
                + "                        </td>\n"
                + "                        <td> <input type=\"text\" name=\"uname\" value=\"\"><br></td>\n"
                + "                    </tr>\n"
                + "                    <tr>\n"
                + "                        <td>\n"
                + "                            <label for=\"password\">Password:</label>\n"
                + "                        </td>\n"
                + "                        <td> <input type=\"password\" name=\"password\" value=\"\"><br>\n"
                + "                    </tr>\n"
                + "                    <tr>\n"
                + "                        <td></td>\n"
                + "                        <td>\n"
                + "                            <input type=\"submit\" value=\"Submit\">\n"
                + "                            <a href=\"registrationControl\">Register</a>\n"
                + "                        </td>\n"
                + "                        </td>\n"
                + "                    </tr>\n"
                + "                </table>\n"
                + "            </form>\n"
                + "            </form>\n"
                + "        </div>";
        if (session.getAttribute("account") != null) {
            AccountEntity account = (AccountEntity) session.getAttribute("account");
            htmls = " <div class=\"tq-div-body\">\n"
                    + "            <h4 style=\"color:#647476\">Welcome"
                    + " <span style=\"color:blue\">"+ account.getUserName() +"</span></h4></div>";
        }
        request.setAttribute("htmls", htmls);
        request.getRequestDispatcher("homePage.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
