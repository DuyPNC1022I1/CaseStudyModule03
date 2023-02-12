package controller;

import dao.OrderDAO;
import model.Cart;
import model.Customer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CheckoutServlet", value = "/checkout")
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Cart cart = null;
        Object o = session.getAttribute("cart");
        if (o != null){
            cart = (Cart) o;
        }else {
            cart = new Cart();
        }
        Customer account = null;
        Object a = session.getAttribute("account");
        if (a != null){
            account = (Customer) a;
            OrderDAO odb = new OrderDAO();
            odb.addOrder(account, cart);
            session.removeAttribute("cart");
            session.setAttribute("size", 0);
            response.sendRedirect("cart.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
