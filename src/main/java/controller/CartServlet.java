package controller;

import model.*;
import service.implementService.BrandServiceImplement;
import service.interfaceService.IProductService;

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
    private final IOrderService iOrderService = new OrderServiceImplement();
    private final OrderDetailDAOImplement detailDAOImplement= new OrderDetailDAOImplement();
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

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        request.getRequestDispatcher("").forward(request,response);
    }
    private void removeProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = iProductService.findById(id);
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        cart.removeIf(item -> item.getProduct().getId() == product.getId());
        int subtotal = 0;
        for (Item item:cart) {
            subtotal += item.getProduct().getPrice() * item.getQuantity();
        }
        session.setAttribute("subtotal", subtotal);
        session.setAttribute("cart",cart);
        request.setAttribute("brands",brands);
        request.getRequestDispatcher("/client/view/cart.jsp").forward(request,response);
    }
    private void checkoutCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        session.setAttribute("cart",cart);
        request.setAttribute("brands",brands);
        request.getRequestDispatcher("/client/view/checkout.jsp").forward(request,response);
    }
    private void payment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userID = (int) session.getAttribute("userID");
        String address = request.getParameter("address");
        String phone = request.getParameter("tel");
        boolean checkOrder = iOrderService.add(new Order(userID,address,phone),userID);
        Order order = iOrderService.findById(userID);
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        for (Item item:cart) {
            OrderDetail orderDetail = new OrderDetail(order.getId(),item.getProduct().getId(),item.getQuantity(),(item.getQuantity() * item.getProduct().getPrice()));
            detailDAOImplement.add(orderDetail);
            iProductService.reduce(item.getQuantity(),item.getProduct().getId());
        }
        session.removeAttribute("cart");
        request.setAttribute("checkOrder",checkOrder);
        request.getRequestDispatcher("/client/view/checkout.jsp").forward(request,response);
    }
    private void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Item> cart;
        if(session.getAttribute("cart") == null){
            cart = new ArrayList<>();
        } else {
            cart = (List<Item>) session.getAttribute("cart");
        }
        int subtotal = 0;
        for (Item item:cart) {
            subtotal += item.getProduct().getPrice() * item.getQuantity();
        }
        session.setAttribute("subtotal", subtotal);
        session.setAttribute("cart",cart);
        request.setAttribute("brands",brands);
        request.getRequestDispatcher("/client/view/cart.jsp").forward(request,response);
    }
}
