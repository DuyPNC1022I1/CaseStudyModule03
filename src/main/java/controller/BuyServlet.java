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

@WebServlet(name = "BuyServlet", value = "/buy")
public class BuyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(true);
        Cart cart = null;
        Object o = session.getAttribute("cart");
//        Có rồi
        if (o != null){
            cart = (Cart) o;
        }else {
            cart = new Cart();
        }
        String tNum = request.getParameter("num");
        String tId = request.getParameter("id");
        int num, id;
        try {
            num = Integer.parseInt(tNum);
            id = Integer.parseInt(tId);
            ProductDAO pdb = new ProductDAO();
            Product p = pdb.selectById(id);
            double price = p.getPrice()*1;
            Item t = new Item(p, num, price);
            cart.addItem(t);
        } catch (NumberFormatException e){
            num = 1;
        }
        List<Item> list = cart.getItems();
        session.setAttribute("cart", cart);
        session.setAttribute("size", list.size());
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}
