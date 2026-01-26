/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

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
public class HomeServlet extends HttpServlet {

    private MobileDao mobileDao;

    @Override
    public void init() throws ServletException {
        mobileDao = new MobileDao();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String minPriceStr = request.getParameter("minPrice");
            String maxPriceStr = request.getParameter("maxPrice");

            List<Mobile> listMobiles = null;
            
            if (minPriceStr != null && !minPriceStr.isEmpty() && 
                maxPriceStr != null && !maxPriceStr.isEmpty()) {
                try {
                    float minPrice = Float.parseFloat(minPriceStr);
                    float maxPrice = Float.parseFloat(maxPriceStr);
                    
                    // Validate price range
                    if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
                        request.setAttribute("error", "Invalid price range. Min price must be less than or equal to max price, and both must be positive.");
                    } else {
                        listMobiles = mobileDao.searchByPriceRange(minPrice, maxPrice);
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Price must be a valid number.");
                }
            } else {
                // Show all available mobiles (notSale = 0)
                List<Mobile> allMobiles = mobileDao.getAllMobiles();
                if (allMobiles != null) {
                    listMobiles = new java.util.ArrayList<>();
                    for (Mobile m : allMobiles) {
                        if (!m.isNotSale()) {
                            listMobiles.add(m);
                        }
                    }
                }
            }

            request.setAttribute("listMobiles", listMobiles);
            request.getRequestDispatcher("home.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while loading mobiles.");
            request.getRequestDispatcher("home.jsp").forward(request, response);
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
