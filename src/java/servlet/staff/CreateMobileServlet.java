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
public class CreateMobileServlet extends HttpServlet {

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
            String mobileName = request.getParameter("mobileName");
            String yearOfProductionStr = request.getParameter("yearOfProduction");
            String quantityStr = request.getParameter("quantity");
            String notSaleStr = request.getParameter("notSale");

            // Validation
            if (mobileId == null || mobileId.trim().isEmpty()) {
                request.setAttribute("error", "Mobile ID is required.");
                request.getRequestDispatcher("/staff/mobile-create.jsp").forward(request, response);
                return;
            }

            if (description == null || description.trim().isEmpty()) {
                request.setAttribute("error", "Description is required.");
                request.getRequestDispatcher("/staff/mobile-create.jsp").forward(request, response);
                return;
            }

            if (mobileName == null || mobileName.trim().isEmpty()) {
                request.setAttribute("error", "Mobile name is required.");
                request.getRequestDispatcher("/staff/mobile-create.jsp").forward(request, response);
                return;
            }

            if (priceStr == null || priceStr.trim().isEmpty()) {
                request.setAttribute("error", "Price is required.");
                request.getRequestDispatcher("/staff/mobile-create.jsp").forward(request, response);
                return;
            }

            // Check if mobile ID already exists
            Mobile existing = mobileDao.getMobileById(mobileId.trim());
            if (existing != null) {
                request.setAttribute("error", "Mobile ID already exists.");
                request.getRequestDispatcher("/staff/mobile-create.jsp").forward(request, response);
                return;
            }

            float price;
            try {
                price = Float.parseFloat(priceStr);
                if (price < 0) {
                    request.setAttribute("error", "Price must be positive.");
                    request.getRequestDispatcher("/staff/mobile-create.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Price must be a valid number.");
                request.getRequestDispatcher("/staff/mobile-create.jsp").forward(request, response);
                return;
            }

            int yearOfProduction = 0;
            if (yearOfProductionStr != null && !yearOfProductionStr.trim().isEmpty()) {
                try {
                    yearOfProduction = Integer.parseInt(yearOfProductionStr);
                } catch (NumberFormatException e) {
                    // Use default 0 if invalid
                }
            }

            int quantity = 0;
            if (quantityStr != null && !quantityStr.trim().isEmpty()) {
                try {
                    quantity = Integer.parseInt(quantityStr);
                    if (quantity < 0) {
                        request.setAttribute("error", "Quantity must be non-negative.");
                        request.getRequestDispatcher("/staff/mobile-create.jsp").forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Quantity must be a valid integer.");
                    request.getRequestDispatcher("/staff/mobile-create.jsp").forward(request, response);
                    return;
                }
            }

            boolean notSale = false;
            if (notSaleStr != null) {
                notSale = "true".equals(notSaleStr) || "1".equals(notSaleStr);
            }

            Mobile mobile = new Mobile(mobileId.trim(), description.trim(), price, 
                    mobileName.trim(), yearOfProduction, quantity, notSale);

            boolean created = mobileDao.createMobile(mobile);

            HttpSession session = request.getSession();
            if (created) {
                request.setAttribute("msg", "Mobile created successfully.");
                response.sendRedirect("mobile-list");
            } else {
                request.setAttribute("error", "Failed to create mobile.");
                request.getRequestDispatcher("/staff/mobile-create.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while creating mobile.");
            request.getRequestDispatcher("/staff/mobile-create.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/staff/mobile-create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
