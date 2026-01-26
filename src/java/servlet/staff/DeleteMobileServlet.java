/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.staff;

import dao.MobileDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nero
 */
public class DeleteMobileServlet extends HttpServlet {

    private MobileDao mobileDao;

    @Override
    public void init() throws ServletException {
        mobileDao = new MobileDao();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mobileId = request.getParameter("mobileId");
        
        if (mobileId == null || mobileId.trim().isEmpty()) {
            request.getSession().setAttribute("error", "Mobile ID is required.");
            response.sendRedirect("mobile-list");
            return;
        }

        boolean deleted = mobileDao.deleteMobile(mobileId.trim());
        
        HttpSession session = request.getSession();
        if (deleted) {
            request.setAttribute("msg", "Mobile deleted successfully.");
        } else {
            session.setAttribute("error", "Failed to delete mobile.");
        }
        
        response.sendRedirect("mobile-list");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
