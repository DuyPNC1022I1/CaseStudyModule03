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

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {
    private ProductDAO productDAO;
    private BrandDAO brandDAO;

    @Override
    public void init() {
        this.productDAO = new ProductDAO();
        this.brandDAO = new BrandDAO<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "view":
                break;
            case "createAcount":
                break;
            case "buy":
                break;
            case "login":
                showLogin(response);
                break;
            default:
                showProduct(request, response);
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
            case "":
                break;
            default:
                break;
        }
    }

    private void showProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products =  this.productDAO.display();
        request.setAttribute("products", products);
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        showBrand(request);
        rd.forward(request, response);
    }

    private void showBrand(HttpServletRequest request) throws ServletException, IOException {
        List<Brand> brands = this.brandDAO.display();
        request.setAttribute("brands", brands);
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
    }

    private void showLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect("login/login-form/login.jsp");
    }

    private void showCreateAcount(HttpServletResponse response) throws IOException {
        response.sendRedirect("");
    }
}
