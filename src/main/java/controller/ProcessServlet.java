package controller;

import dao.ProductDAO;
import model.Cart;
import model.Item;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProcessServlet", value = "/process")
public class ProcessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Cart cart = null;
        Object o = session.getAttribute("cart");
        if (o != null){
            cart = (Cart) o;
        }else {
            cart = new Cart();
        }
        String tNum = request.getParameter("num").trim();
        String tId = request.getParameter("id");
        int num, id;
        try {
            id = Integer.parseInt(tId);
            num = Integer.parseInt(tNum);
            if ((num == -1) && (cart.getQuantityById(id)) <= 1) {
                cart.removeItem(id);
            } else {
                ProductDAO pdb = new ProductDAO();
                Product p = pdb.selectById(id);
                double price = p.getPrice() * 1;
                Item t = new Item(p, num, price);
                cart.addItem(t);
            }
        }   catch (NumberFormatException e){
            System.out.println(e);
        }
        List<Item> list = cart.getItems();
        session.setAttribute("cart", cart);
        session.setAttribute("size", list.size());
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =request.getSession(true);
        Cart cart = null;
        Object o = session.getAttribute("cart");
        if (o != null){
            cart = (Cart) o;
        }else {
            cart = new Cart();
        }
        int id = Integer.parseInt(request.getParameter("id"));
        cart.removeItem(id);
        List<Item> list = cart.getItems();
        session.setAttribute("cart", cart);
        session.setAttribute("size", list.size());
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}
