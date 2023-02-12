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

    private void display(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("products", this.productDAO.display());
        request.setAttribute("brands", this.brandDAO.display());
        RequestDispatcher rd = request.getRequestDispatcher("/admin.jsp");
        rd.forward(request, response);
    }
    private void showCreatForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("brands", this.brandDAO.display());
        RequestDispatcher rd = request.getRequestDispatcher("createProduct.jsp");
        showBrand(request);
        rd.forward(request, response);
    }
    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        double price = 0;
        int quantity = 0;
        try {
            price = Double.parseDouble(request.getParameter("price"));
            quantity = Integer.parseInt(request.getParameter("quantity"));
        }catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        String brand = request.getParameter("brand");

        productDAO.create(new Product(name, price, quantity, description, image, brandDAO.selectByName(brand)));
        response.sendRedirect("/admin");
    }

    private void showUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("product", this.productDAO.selectById(id));
        request.setAttribute("brands", this.brandDAO.display());
        RequestDispatcher rd = request.getRequestDispatcher("editProduct.jsp");
        rd.forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        double price = 0;
        int quantity = 0;
        try {
            price = Double.parseDouble(request.getParameter("price"));
            quantity = Integer.parseInt(request.getParameter("quantity"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        String brand = request.getParameter("brand");

        productDAO.update(new Product(id, name, price, quantity, description, image, brandDAO.selectByName(brand)));
        response.sendRedirect("/admin");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productDAO.delete(id);
        response.sendRedirect("/admin");
    }

    private void showBrand(HttpServletRequest request) {
        List<Brand> brands = this.brandDAO.display();
        request.setAttribute("brands", brands);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("createProduct.jsp");
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
        RequestDispatcher rd = request.getRequestDispatcher("/admin.jsp");
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
        RequestDispatcher rd = request.getRequestDispatcher("/admin.jsp");
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
        RequestDispatcher rd = request.getRequestDispatcher("/admin.jsp");
        rd.forward(request, response);
    }
}
