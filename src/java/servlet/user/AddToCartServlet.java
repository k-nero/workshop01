/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.user;

import dao.MobileDao;
import dto.Mobile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nero
 */
public class AddToCartServlet extends HttpServlet {

    private MobileDao mobileDao;

    @Override
    public void init() throws ServletException {
        mobileDao = new MobileDao();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mobileId = request.getParameter("mobileId");
        
        if (mobileId == null || mobileId.trim().isEmpty()) {
            request.setAttribute("error", "Mobile ID is required.");
            response.sendRedirect("../home");
            return;
        }

        Mobile mobile = mobileDao.getMobileById(mobileId.trim());
        
        if (mobile == null) {
            request.setAttribute("error", "Mobile not found.");
            response.sendRedirect("../home");
            return;
        }

        if (mobile.isNotSale()) {
            request.setAttribute("error", "This mobile is not available for sale.");
            response.sendRedirect("../home");
            return;
        }

        HttpSession session = request.getSession();
        List<Mobile> cart = (List<Mobile>) session.getAttribute("cart");
        
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        // Check if already in cart
        boolean exists = false;
        for (Mobile m : cart) {
            if (m.getMobileId().equals(mobileId)) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            cart.add(mobile);
            request.setAttribute("msg", "Mobile added to cart successfully.");
        } else {
            request.setAttribute("msg", "Mobile is already in cart.");
        }

        request.getRequestDispatcher("../home").forward(request, response);
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
