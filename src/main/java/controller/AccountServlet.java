package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "AccountServlet", value = "/AccountServlet")
public class AccountServlet extends HttpServlet {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/case_module03.account?useSSL=false";
    private static final String USER = "database_name";
    private static final String PASS = "database_pass";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userRole = (Integer) session.getAttribute("rol");
        if (userRole != null) {
            if (userRole == 1) {
                response.sendRedirect("admin.jsp");
            } else if (userRole == 0) {
                response.sendRedirect("user.jsp");
            }
        } else {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username != null && password != null) {
                try {
                    Class.forName(JDBC_DRIVER);
                    Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
                    PreparedStatement statement = connection.prepareStatement(
                            "SELECT rol FROM user WHERE name = ? AND pass = ?");
                    statement.setString(1, username);
                    statement.setString(2, password);

                    ResultSet result = statement.executeQuery();
                    if (result.next()) {
                        int role = result.getInt("rol");
                        session.setAttribute("rol", role);
                        if (role == 1) {
                            response.sendRedirect("admin.jsp");
                        } else if (role == 0) {
                            response.sendRedirect("user.jsp");
                        }
                    } else {
                        response.sendRedirect("index.jsp");
                    }
                    connection.close();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new ServletException("Error accessing database", e);
                }
            } else {
                response.sendRedirect("login.jsp");
            }
        }
    }

}
