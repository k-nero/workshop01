/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.UserDao;
import dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nero
 */
public class LoginServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
    }

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
            String userId = request.getParameter("userId");
            String passwordStr = request.getParameter("password");
            
            if (userId == null || passwordStr == null || userId.trim().isEmpty() || passwordStr.trim().isEmpty()) {
                request.setAttribute("error", "User ID and password are required.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            
            try {
                int password = Integer.parseInt(passwordStr);
                User user = userDao.getByUserId(userId.trim());

                if (user == null) {
                    request.setAttribute("error", "Invalid user ID or password.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                } else if (user.getPassword() != password) {
                    request.setAttribute("error", "Invalid user ID or password.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                } else {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);
                    session.setAttribute("role", user.getRole());
                    
                    // Redirect based on role: 1=manager, 2=staff, 0=user
                    if (user.getRole() == 1 || user.getRole() == 2) {
                        response.sendRedirect("staff/mobile-list");
                    } else {
                        response.sendRedirect("home");
                    }
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Password must be a number.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Login Servlet";
    }
}
