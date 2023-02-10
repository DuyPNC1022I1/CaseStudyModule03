package controller;

import dao.BrandDAO;
import dao.ProductDAO;
import model.Brand;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
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
            case "createAccount":
                showCreateAcount(response);
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
            case "searchName":
                searchByName(request, response);
                break;
            default:
                showProduct(request, response);
                break;
        }
    }

    private void showProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = this.productDAO.display();
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

    //Tìm + Hiển thị sản phẩm theo tên
    private void searchByName(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("searchByName");
        //Tạo biến điều kiện để hiển thị kết quả
        boolean flag = false;
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < productDAO.display().size(); i++) {
            if (productDAO.display().get(i).getName().toUpperCase().contains(name.toUpperCase())) {
                products.add(productDAO.display().get(i));
                flag = true;
            }
        }
        request.setAttribute("flag", flag);
        request.setAttribute("products", products);
        RequestDispatcher rd = request.getRequestDispatcher("user/displaySearch.jsp");
        rd.forward(request, response);
    }

    private void searchByBrand(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String brandName = request.getParameter("searchByBrand");

        //Tạo list products chứa các product có trường brand tương ứng
        List<Product> products = new ArrayList<>();

        //Tạo flag. Flad = true nếu tìm thấy sản phẩm có brand tương ứng
        boolean flag = false;
        for (int i = 0; i < productDAO.display().size(); i++) {
            if (productDAO.display().get(i).getBrand().getName().equals(brandName)) {
                products.add(productDAO.display().get(i));
                flag = true;
            }
        }
        request.setAttribute("flag", flag);
        request.setAttribute("products", products);
        RequestDispatcher rd = request.getRequestDispatcher("user/displaySearch.jsp");
        rd.forward(request, response);
    }

    //Tìm sản phẩm theo giá tiền
    private void searchByPrice(HttpServletRequest request, HttpServletResponse response) {

    }

}
