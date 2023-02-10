package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "AccountServlet", value = "/AccountServlet")
public class AccountServlet extends HttpServlet {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/case_module03.account?useSSL=false";
    private static final String USER = "database_name";
    private static final String PASS = "database_pass";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Connection connection = null;
        PreparedStatement acc = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "select * from account where name = ? and pass = ?";
            acc = connection.prepareStatement(sql);
            acc.setString(1, username);
            acc.setString(2, password);
            ResultSet resultSet = acc.executeQuery();
            if (resultSet.next()) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/new_page.jsp");
                requestDispatcher.forward(request, response);
            } else {
                response.sendRedirect("/index.jsp");
            }
            if (acc != null) {
                try {
                    acc.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
