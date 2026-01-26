/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.staff;

import dao.MobileDao;
import dto.Mobile;
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
public class UpdateMobileServlet extends HttpServlet {

    private MobileDao mobileDao;

    @Override
    public void init() throws ServletException {
        mobileDao = new MobileDao();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String mobileId = request.getParameter("mobileId");
            String description = request.getParameter("description");
            String priceStr = request.getParameter("price");
            String quantityStr = request.getParameter("quantity");
            String notSaleStr = request.getParameter("notSale");

            HttpSession session = request.getSession();
            
            // Validation
            if (mobileId == null || mobileId.trim().isEmpty()) {
                request.setAttribute("error", "Mobile ID is required.");
                response.sendRedirect("mobile-list");
                return;
            }

            Mobile mobile = mobileDao.getMobileById(mobileId.trim());
            if (mobile == null) {
                request.setAttribute("error", "Mobile not found.");
                response.sendRedirect("mobile-list");
                return;
            }

            // Update fields
            if (description != null && !description.trim().isEmpty()) {
                mobile.setDescription(description.trim());
            }

            if (priceStr != null && !priceStr.trim().isEmpty()) {
                try {
                    float price = Float.parseFloat(priceStr);
                    if (price < 0) {
                        request.setAttribute("error", "Price must be positive.");
                        response.sendRedirect("mobile-list");
                        return;
                    }
                    mobile.setPrice(price);
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Price must be a valid number.");
                    response.sendRedirect("mobile-list");
                    return;
                }
            }

            if (quantityStr != null && !quantityStr.trim().isEmpty()) {
                try {
                    int quantity = Integer.parseInt(quantityStr);
                    if (quantity < 0) {
                        request.setAttribute("error", "Quantity must be non-negative.");
                        response.sendRedirect("mobile-list");
                        return;
                    }
                    mobile.setQuantity(quantity);
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Quantity must be a valid integer.");
                    response.sendRedirect("mobile-list");
                    return;
                }
            }

            if (notSaleStr != null) {
                mobile.setNotSale("true".equals(notSaleStr) || "1".equals(notSaleStr));
            }

            boolean updated = mobileDao.updateMobile(mobile);

            if (updated) {
                request.setAttribute("msg", "Mobile updated successfully.");
            } else {
                request.setAttribute("error", "Failed to update mobile.");
            }

            response.sendRedirect("mobile-list");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while updating mobile.");
            response.sendRedirect("mobile-list");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mobileId = request.getParameter("mobileId");
        if (mobileId != null) {
            Mobile mobile = mobileDao.getMobileById(mobileId);
            request.setAttribute("mobile", mobile);
            request.getRequestDispatcher("/staff/mobile-update.jsp").forward(request, response);
        } else {
            response.sendRedirect("mobile-list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
