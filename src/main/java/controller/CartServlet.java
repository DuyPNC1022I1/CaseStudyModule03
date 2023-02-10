package controller;

import model.Item;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
    private final IProductService iProductService = new ProductServiceImplement();
    private final List<Brand> brands = new BrandServiceImplement().getAll();
//    private final IOrderService iOrderService = new OrderServiceImplement();
//    private final OrderDetailDAOImplement detailDAOImplement= new OrderDetailDAOImplement();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        action(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        action(request, response);
    }
    private void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "";

        switch (action) {
            case "add":
                addToCart(request,response);
                break;
            case "remove":
                removeProduct(request,response);
                break;
            case "checkout":
                checkoutCart(request,response);
                break;
            case "payment":
                payment(request,response);
                break;
            default:
                displayCart(request,response);
        }
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session =request.getSession();
    int id = Integer.parseInt(request.getParameter("id"));
    int quantity = 1;
    Product product = iProductService.findById(id);
        List<Item> cart;
        if (request.getParameter("quantity") != null){
            quantity = Integer.parseInt(request.getParameter("quantity"));
        }
        if (session.getAttribute("cart") == null){
            cart = new ArrayList<>();
            cart.add(new Item(product,quantity));
        }else {
            cart = (List<Item>) session.getAttribute("cart");
            boolean check = false;
            for (Item item : cart) {
                if (item.getProduct().getId() == product.getId()){
                    item.setQuantity(item.getQuantity() + quantity);
                    check = true;
                }
            }
            if (check == false){
                cart.add(new Item(product,quantity));
            }
        }
        int subtotal = 0;
        for (Item item : cart) {
            subtotal += item.getProduct().getPrice() * item.getQuantity();
        }
        session.setAttribute("subtotal", subtotal);
        session.setAttribute("cart", cart);
        request.setAttribute("brands", brands);
        request.getRequestDispatcher("").forward(request, response);
    }
}
