/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.user;

import dto.Mobile;
import java.io.IOException;
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
public class RemoveFromCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mobileId = request.getParameter("mobileId");
        
        if (mobileId == null || mobileId.trim().isEmpty()) {
            request.getSession().setAttribute("error", "Mobile ID is required.");
            response.sendRedirect("cart");
            return;
        }

        HttpSession session = request.getSession();
        List<Mobile> cart = (List<Mobile>) session.getAttribute("cart");
        
        if (cart != null) {
            for (int i = 0; i < cart.size(); i++) {
                if (cart.get(i).getMobileId().equals(mobileId.trim())) {
                    cart.remove(i);
                    break;
                }
            }
            request.setAttribute("msg", "Mobile removed from cart.");
        }

        request.getRequestDispatcher("cart").forward(request, response);
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
