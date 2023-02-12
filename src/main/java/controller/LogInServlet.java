package controller;

import dao.AccountDAO;
import model.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "loginServlet", value = "/login")
public class LogInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            loginProcess(request,response);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            loginProcess(request,response);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void loginProcess(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.checkLogin(name, password);
        if (account==null){
            request.setAttribute("messLogin","Wrong username or Password, enter again");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("account",account);
            response.sendRedirect("home");
        }
    }
}
