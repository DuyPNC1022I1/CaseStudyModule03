package controller;

import dao.BrandDAO;
import dao.ProductDAO;
import model.Brand;
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
            case "showUpdate":
                showUpdate(request, response);
                break;
            case "delete":
                delete(request, response);
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
            case "update":
                update(request, response);
                break;
            default:
                display(request, response);
                break;
        }
    }

    private void display(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("products", this.productDAO.display());
        request.setAttribute("brands", this.brandDAO.display());
        RequestDispatcher rd = request.getRequestDispatcher("/admin.jsp");
        rd.forward(request, response);
    }
    private void showCreatForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("brands", this.brandDAO.display());
        RequestDispatcher rd = request.getRequestDispatcher("createProduct.jsp");
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

    private void showUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("product", this.productDAO.selectById(id));
        request.setAttribute("brands", this.brandDAO.display());
        RequestDispatcher rd = request.getRequestDispatcher("editProduct.jsp");
        rd.forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = 0;
        String name = "";
        double price = 0;
        int quantity = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            name = request.getParameter("name");
            price = Double.parseDouble(request.getParameter("price"));
            quantity = Integer.parseInt(request.getParameter("quantity"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        String brand = request.getParameter("brand");

        productDAO.update(new Product(id, name, price, quantity, description, image, brandDAO.selectByName(brand)));
        response.sendRedirect("/admin.jsp");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productDAO.delete(id);
        response.sendRedirect("/admin");
    }

    private void showBrand(HttpServletRequest request) {
        List<Brand> brands = this.brandDAO.display();
        request.setAttribute("brands", brands);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin.jsp");
    }
}
