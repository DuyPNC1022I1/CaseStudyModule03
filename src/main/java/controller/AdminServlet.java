package controller;

import dao.BrandDAO;
import dao.ProductDAO;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {

    ProductDAO productDAO;
    BrandDAO brandDAO;

    @Override
    public void init() {
        this.productDAO = new ProductDAO();
        this.brandDAO = new BrandDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "showCreate":
                showCreatForm(request, response);
                break;
            case "update":
                break;
            case "delete":
                break;
            default:
                display(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            default:
                break;
        }
    }

    private void display(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Product> products =  this.productDAO.display();
        request.setAttribute("products", products);
        RequestDispatcher rd = request.getRequestDispatcher("admin/display.jsp");
        rd.forward(request, response);
    }
    private void showCreatForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("brands", brandDAO.display());
        RequestDispatcher rd = request.getRequestDispatcher("admin/create.jsp");
        rd.forward(request, response);
    }
    private void createProduct(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        String brand = request.getParameter("brand");

        productDAO.create(new Product(name, price, quantity, description, image, brandDAO.selectByName(brand)));
    }
}
