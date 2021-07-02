/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import entity.AccountEntity;
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
@WebServlet(name = "registrationControl", urlPatterns = {"/registrationControl"})
public class RegistrationController extends HttpServlet {

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

        /*After the user creates an account, it will display a successful 
        or failed account creation message*/
        String message = (String) session.getAttribute("message");
        String color ="";
        if (message == null) {
            message = "";
        } else {
            color = message.contains("successfully") ? "green" : "red";
        }
        String htmls = "<div class=\"div-body div-login-res\">\n"
                + "            <h4>Registration Form</h4>\n"
                + "            <br>\n<h5 style=\"color:" + color + "\">" + message + "</h5>"
                + "            <form action=\"registrationControl\" method=\"post\">\n"
                + "                <table>\n"
                + "                    <tr>\n"
                + "                        <td>\n"
                + "                            <label for=\"fname\">User Name:</label>\n"
                + "                        </td>\n"
                + "                        <td> <input type=\"text\" name=\"uname\" value=\"\"><br></td>\n"
                + "                    </tr>\n"
                + "                    <tr>\n"
                + "                        <td>\n"
                + "                            <label for=\"lname\">Password:</label>\n"
                + "                        </td>\n"
                + "                        <td> <input type=\"text\" name=\"pass\" value=\"\"><br>\n"
                + "                    </tr>\n"
                + "                    <tr>\n"
                + "                        <td>\n"
                + "                            <label for=\"eyecolor\">User Type: </label>\n"
                + "\n"
                + "                        </td>\n"
                + "                        <td>\n"
                + "                            <select name=\"role\">\n"
                + "                                <option value=\"Teacher\">Teacher</option>\n"
                + "                                <option value=\"Normal\">Normal</option>\n"
                + "                            </select>\n"
                + "                        </td>\n"
                + "                    </tr>\n"
                + "                    <tr>\n"
                + "                        <td>\n"
                + "                            <label for=\"email\">Email:</label>\n"
                + "                        </td>\n"
                + "                        <td> <input type=\"email\" name=\"email\" value=\"\"><br>\n"
                + "                    </tr>\n"
                + "                    <tr>\n"
                + "                        <td></td>\n"
                + "                        <td>\n"
                + "                            <input type=\"submit\" value=\"Register\">\n"
                + "                        </td>\n"
                + "                        </td>\n"
                + "                    </tr>\n"
                + "                </table>\n"
                + "            </form>\n"
                + "        </div>";
        request.setAttribute("htmls", htmls);
        request.getRequestDispatcher("homePage.jsp").forward(request, response);

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
        //get all value user input to form sign up
        HttpSession session = request.getSession();
        String userName = request.getParameter("uname");
        String passWord = request.getParameter("pass");
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        if(userName.equals("")||passWord.equals("")||role.equals("")||email.equals("")){
            session.setAttribute("message", "Account failure created! Input all infomation!");
            response.sendRedirect("registrationControl");
            return;
        }
        //remove messager if user re-sign up
        
        if (session.getAttribute("message") != null) {
            session.removeAttribute("message");
        }
        AccountDAO accountDAO = new AccountDAO();

        //check acount exist, if account already exist user can't create this account
        boolean checkAccountExist = accountDAO.checkAccountExist(userName);
        if (checkAccountExist) {
            session.setAttribute("message", "Account already exist!");
            System.out.println("faillllllllll");
        } else {
            //set all atrribute to account enity
            AccountEntity account = new AccountEntity();

            account.setUserName(userName);
            account.setPassWord(passWord);
            int roleUser = role.equals("Teacher") ? 1 : 0;
            account.setRoleUser(roleUser);
            account.setEmail(email);

            //push account data to database
            int checkSuccess = accountDAO.signUp(account);
            if (checkSuccess != 0) {
                session.setAttribute("message", "Account successfully created");
            } else {
                session.setAttribute("message", "Account failure created");
            }
        }
        response.sendRedirect("registrationControl");

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
