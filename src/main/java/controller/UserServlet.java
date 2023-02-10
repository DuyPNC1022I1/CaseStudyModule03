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
        this.brandDAO = new BrandDAO();
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
                showCreateAccount(response);
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
            case "searchByBrand":
                searchByBrand(request, response);
                break;
            case "searchPrice":
                searchByPrice(request, response);
                break;
            default:
                showProduct(request, response);
                break;
        }
    }

    private void showProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("searchByName");
        List<Product> products = new ArrayList<>();
        boolean flag = false;
        if (name == null) {
            products = this.productDAO.display();
        } else {

            for (int i = 0; i < productDAO.display().size(); i++) {
                if (productDAO.display().get(i).getName().toUpperCase().contains(name.toUpperCase())) {
                    products.add(productDAO.display().get(i));
                    flag = true;
                }
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        List<Brand> brands = this.brandDAO.display();
        request.setAttribute("flag", flag);
        request.setAttribute("brands", brands);
        request.setAttribute("products", products);
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

    private void showCreateAccount(HttpServletResponse response) throws IOException {
        response.sendRedirect("/login/login-form/Sigup.jsp");
    }

    //Tìm + Hiển thị sản phẩm theo tên
    private void searchByName(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("searchByName");
        //Tạo biến điều kiện để hiển thị kết quả
        boolean flag = false;
        List<Product> productsByName = new ArrayList<>();
        for (int i = 0; i < productDAO.display().size(); i++) {
            if (productDAO.display().get(i).getName().toUpperCase().contains(name.toUpperCase())) {
                productsByName.add(productDAO.display().get(i));
                flag = true;
            }
        }
        request.setAttribute("flag", flag);
        request.setAttribute("productsByName", productsByName);
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }

    //Tìm + Hiển thị sản phẩm theo brand
    private void searchByBrand(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String brandName = request.getParameter("searchByBrand");

        //Tạo list products chứa các product có trường brand tương ứng
        List<Product> productsByBrand = new ArrayList<>();

        //Tạo flag. Flad = true nếu tìm thấy sản phẩm có brand tương ứng
        boolean flag = false;
        for (int i = 0; i < productDAO.display().size(); i++) {
            if (productDAO.display().get(i).getBrand().getName().equals(brandName)) {
                productsByBrand.add(productDAO.display().get(i));
                flag = true;
            }
        }
        request.setAttribute("flag", flag);
        request.setAttribute("productsByBrand", productsByBrand);
        RequestDispatcher rd = request.getRequestDispatcher("user/displaySearch.jsp");
        rd.forward(request, response);
    }

    //Tìm + Hiển thị sản phẩm theo giá tiền
    private void searchByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double lowerPrice = Double.parseDouble(request.getParameter("lowerPrice"));
        double upperPrice = Double.parseDouble(request.getParameter("upperPrice"));
        List<Product> productsByPrice = new ArrayList<>();
        boolean flag = false;
        for (int i = 0; i < productDAO.display().size(); i++) {
            if ((productDAO.display().get(i).getPrice() >= lowerPrice) && (productDAO.display().get(i).getPrice() <= upperPrice)) {
                productsByPrice.add(productDAO.display().get(i));
                flag = true;
            }
        }
        request.setAttribute("flag", flag);
        request.setAttribute("productsByPrice", productsByPrice);
        RequestDispatcher rd = request.getRequestDispatcher("user/displaySearch.jsp");
        rd.forward(request, response);
    }

}
