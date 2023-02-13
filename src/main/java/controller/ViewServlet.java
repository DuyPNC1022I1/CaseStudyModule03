package controller;

import dao.AccountDAO;
import dao.BrandDAO;
import dao.ProductDAO;
import model.Account;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ViewServlet", value = "/view")
public class ViewServlet extends HttpServlet {
    AccountDAO accountDAO;
    private ProductDAO productDAO;
    private BrandDAO brandDAO;

    @Override
    public void init() {
        this.productDAO = new ProductDAO();
        this.brandDAO = new BrandDAO();
        this.accountDAO = new AccountDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "showLogin":
                showLogin(request,response);
                break;
            case "logOut":
                logOut(request, response);
                break;
            case "showCreate":
                showCreate(request, response);
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
            case "login":
                try {
                    login(request, response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "create" :
                create(request , response);
                break;
            case "searchByBrand":
                searchByBrand(request, response);
                break;
            case "searchByPrice":
                searchByPrice(request, response);
                break;
            case "searchByName":
                searchByName(request, response);
                break;
            default:
                display(request, response);
                break;
        }
    }

    private void display(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", this.productDAO.display());
        request.setAttribute("brands", this.brandDAO.display());
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }

    private void showLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("login/login-form/login.jsp");
    }
    public void login(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.checkLogin(name, password);
        if (account == null){
            request.setAttribute("messLogin","Wrong username or Password, enter again");
            RequestDispatcher rq = request.getRequestDispatcher("/login/login-form/login.jsp");
            rq.forward(request, response);
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("admin",account);
            if (account.getRole() == 1) {
                response.sendRedirect("/admin");
            } else if (account.getRole() == 0){
                response.sendRedirect("/user");
            }
        }
    }
    public void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("account");
        response.sendRedirect("index.jsp");
    }
    private void showCreate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("login/login-form/sigup.jsp");
    }
    private void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        accountDAO.create(new Account(name, pass, phone, email, address));
        response.sendRedirect("/login");
    }

    private void searchByName(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("searchByName");
        //Tạo biến điều kiện để hiển thị kết quả
        boolean flag = false;
        //Tạo biến chọn phần hiển thị tìm kiếm trên màn hình: 1: searchByName, 2: searchByBrand, 3: searchByPrice
        int choose = 1;
        List<Product> productsByName = new ArrayList<>();
        for (int i = 0; i < productDAO.display().size(); i++) {
            if (productDAO.display().get(i).getName().toUpperCase().contains(name.toUpperCase())) {
                productsByName.add(productDAO.display().get(i));
                flag = true;
            }
        }
        request.setAttribute("choose", choose);
        request.setAttribute("status", flag);
        request.setAttribute("flag", flag);
        request.setAttribute("brands", brandDAO.display());
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
        //Tạo flag. Flag = true nếu tìm thấy sản phẩm có brand tương ứng
        boolean flag = false;
        //Tạo biến chọn phần hiển thị tìm kiếm trên màn hình: 1: searchByName, 2: searchByBrand, 3: searchByPrice
        int choose = 2;
        for (int i = 0; i < productDAO.display().size(); i++) {
            if (productDAO.display().get(i).getBrand().getName().equals(brandName)) {
                productsByBrand.add(productDAO.display().get(i));
                flag = true;
            }
        }
        request.setAttribute("brands", brandDAO.display());
        request.setAttribute("flag", flag);
        request.setAttribute("choose", choose);
        request.setAttribute("productsByBrand", productsByBrand);
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }

    //Tìm + Hiển thị sản phẩm theo giá tiền
    private void searchByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double lowerPrice = 0;
        double upperPrice = 0;
        //Tạo biến hiển hiển thị lỗi khi nguười dùng nhập sai kiểu dữ liệu
        boolean err = true;
        try {
            lowerPrice = Double.parseDouble(request.getParameter("lowerPrice"));
            upperPrice = Double.parseDouble(request.getParameter("upperPrice"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            err = false;
        }
        List<Product> productsByPrice = new ArrayList<>();
        boolean flag = false;
        //Tạo biến chọn phần hiển thị tìm kiếm trên màn hình: 1: searchByName, 2: searchByBrand, 3: sarchByPrice
        int choose = 3;
        for (int i = 0; i < productDAO.display().size(); i++) {
            if ((productDAO.display().get(i).getPrice() >= lowerPrice) && (productDAO.display().get(i).getPrice() <= upperPrice)) {
                productsByPrice.add(productDAO.display().get(i));
                flag = true;
            }
        }
        request.setAttribute("err", err);
        request.setAttribute("flag", flag);
        request.setAttribute("choose", choose);
        request.setAttribute("brands", brandDAO.display());
        request.setAttribute("productsByPrice", productsByPrice);
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }
}
