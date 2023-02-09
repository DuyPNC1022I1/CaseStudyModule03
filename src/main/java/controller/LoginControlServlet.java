//package controller;
//
//import dao.Account;
//import dao.LoginDAO;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//
//@WebServlet(name = "LoginControlServlet", value = "/LoginControlServlet")
//public class LoginControlServlet extends HttpServlet {
////    @Override
////    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////    response.setContentType();
////    try {
////        String user = request.getParameter("username");
////        String pass = request.getParameter("password");
////        LoginDAO loginDAO = new LoginDAO();
////        Account account = loginDAO.checkLogin(user, pass);
////        if (account == null){
////            response.sendRedirect("Login.jsp");
////        }else {
////            response.sendRedirect("Success.jsp");
////        }
////    } catch (Exception e) {
////    }
////    }
////    @Override
////    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////
////    }
////}
