package controller;
import dao.AccountDAO;
import dao.BrandDAO;
import dao.ProductDAO;
import model.Account;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

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
                showLogin(request, response);
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
                loginUser(request, response);
                break;
            case "create":
                create(request, response);
                break;
            case "showLogin":
                showLogin(request, response);
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

    private void showLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.sendRedirect("login/login-form/login.jsp");
        RequestDispatcher rd = request.getRequestDispatcher("/login/login-form/login.jsp");
        rd.forward(request, response);
    }

    public void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = accountDAO.checkLogin(name, password);
        if (account == null) {
            request.setAttribute("messLogin", "Wrong username or Password, enter again");
            request.getRequestDispatcher("/view?action=showLogin").forward(request,response);

        }
        else {
            System.out.println(account);
            HttpSession session = request.getSession();
            session.setAttribute("admin", account);
            if (account.getRole() == 1) {
                response.sendRedirect("/admin");
            } else if (account.getRole() == 0) {
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
}
