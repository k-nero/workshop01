/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.staff;

import dao.MobileDao;
import dto.Mobile;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nero
 */
public class MobileListServlet extends HttpServlet {

    private MobileDao mobileDao;

    @Override
    public void init() throws ServletException {
        mobileDao = new MobileDao();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String searchTerm = request.getParameter("search");
            List<Mobile> listMobiles = null;

            if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                listMobiles = mobileDao.searchByIdOrName(searchTerm.trim());
            } else {
                listMobiles = mobileDao.getAllMobiles();
            }

            request.setAttribute("listMobiles", listMobiles);
            request.setAttribute("searchTerm", searchTerm);
            request.getRequestDispatcher("/staff/mobile-list.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "An error occurred while loading mobiles.");
            request.getRequestDispatcher("/staff/mobile-list.jsp").forward(request, response);
        }
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
