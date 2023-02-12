package dao;

import model.Customer;

import java.sql.*;

public class CustomerDAO {
    static String  jdbcURL = "jdbc:mysql://localhost:3306/case_module03.account";
    static String jdbcUsername = "root";
    static String jdbcPassword = "12345678";

    public Customer getAccount(String user, String pass) {
        String query = "select * from customer" + "where userName= ? and passWord = ?";
//        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, user);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
