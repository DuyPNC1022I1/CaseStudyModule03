package controller;

import model.Account;
import service.AccountService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AccountServlet", value = "/AccountServlet")
public class AccountServlet extends HttpServlet {
        AccountService accountService = new AccountService();

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String action = request.getParameter("action");
            if (action == null) {
                action = "";
            }
            switch (action) {
                case "create":
                    showCreateAccount(request, response);
                    break;
                case "update":
                    showUpdateAccount(request, response);
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
                    createAccount(request, response);
                    break;
                case "update":
                    updateAccount(request, response);
                    break;
                default:
                    break;
            }
        }

        private void updateAccount(HttpServletRequest request, HttpServletResponse response) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String passWord = request.getParameter("password");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            Account account = new Account(id, name , passWord, phone, email, address);
            RequestDispatcher dispatcher;
            account.setName();
            account.setPass(passWord);
            account.setPhone(phone);
            account.setEmail(email);
            account.setAddress(address);
            this.accountService.updateCustomer( id , account);
            request.setAttribute("accounts", account);
            request.setAttribute("message", "Update success");
            dispatcher = request.getRequestDispatcher("/forgotpassword.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void createAccount(HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("email");
            String passWord = request.getParameter("password");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            Account account = new Account(id, name, passWord, phone, email, address);
            this.accountService.addCustomer(account);
            request.setAttribute("accounts", account);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Sigup.jsp");
            request.setAttribute("message", "New customer was created");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void listAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<Account> accounts = this.accountService.findAll();
            RequestDispatcher dispatcher = request.getRequestDispatcher("");
            request.setAttribute("account", accounts);
            dispatcher.forward(request, response);
        }

        private void showCreateAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Sigup.jsp");
            dispatcher.forward(request, response);
        }

    private void showUpdateAccount(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Account account = this.accountService.findById(id);
        RequestDispatcher dispatcher;
        if (account == null) {
            dispatcher = request.getRequestDispatcher("");
        } else {
            request.setAttribute("customers", account);
            dispatcher = request.getRequestDispatcher("/updatePassWord.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
        }