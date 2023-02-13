package controller;

import dao.BrandDAO;
import dao.ProductDAO;
import model.Brand;
import model.Cart;
import model.Item;
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
            case "buy":
                buyProduct(request, response);
                break;
            case "showBuy":
//                showBuy(request, response);
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
            case "searchByPrice":
                searchByPrice(request, response);
                break;
            case "searchByName":
                searchByName(request, response);
                break;
            default:
                showProduct(request, response);
                break;
        }
    }

    private void showProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", this.productDAO.display());
        request.setAttribute("brands", this.brandDAO.display());
        RequestDispatcher rd = request.getRequestDispatcher("/user.jsp");
        rd.forward(request, response);

    }

    private void showBrand(HttpServletRequest request) throws ServletException, IOException {
        List<Brand> brands = this.brandDAO.display();
        request.setAttribute("brands", brands);
        RequestDispatcher rd = request.getRequestDispatcher("/user.jsp");
    }

    //Tìm + Hiển thị sản phẩm theo tên
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
        RequestDispatcher rd = request.getRequestDispatcher("/user.jsp");
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
        RequestDispatcher rd = request.getRequestDispatcher("/user.jsp");
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
        RequestDispatcher rd = request.getRequestDispatcher("/user.jsp");
        rd.forward(request, response);
    }

    private void buyProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Cart cart = null;
        Object o = session.getAttribute("cart");
//        Có rồi
        if (o != null) {
            cart = (Cart) o;
        } else {
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
            double price = p.getPrice() * 1;
            Item t = new Item(p, num, price);
            cart.addItem(t);
        } catch (NumberFormatException e) {
            num = 1;
        }
        List<Item> list = cart.getItems();
        session.setAttribute("cart", cart);
        session.setAttribute("size", list.size());
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}
