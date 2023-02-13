package dao;

import model.Cart;
import model.Customer;
import model.Item;

import java.sql.*;
import java.time.LocalDate;

public class OrderDAO {
    static String jdbcURL = "jdbc:mysql://localhost:3306/case_module03.account";
    static String jdbcUsername = "root";
    static String jdbcPassword = "123456";

    public void addOrder(Customer c, Cart cart) {
        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
            //add order
            String sql = "insert into cart values(?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, date);
            st.setInt(2, c.getId());
            st.setDouble(3, cart.getTotalMoney());
            st.executeUpdate();
            //lay id cua order vua add
            String sql1 = "select top 1 id from cart order by id desc";
            PreparedStatement st1 = conn.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();
            //add bang OrderDetail
            if (rs.next()) {
                int oid = rs.getInt("id");
                for (Item i : cart.getItems()) {
                    String sql2 = "insert into bill values(?,?,?,?)";
                    PreparedStatement st2 = conn.prepareStatement(sql2);
                    st2.setInt(1, oid);
                    st2.setInt(2, i.getProduct().getId());
                    st2.setInt(3, i.getQuantity());
                    st2.setDouble(4, i.getPrice());
                    st2.executeUpdate();
                }
            }
            //cap nhat lai so luong san pham
            String sql3 = "update product set quantity=quantity-? where id=?";
            PreparedStatement st3 = conn.prepareStatement(sql3);
            for (Item i : cart.getItems()) {
                st3.setInt(1, i.getQuantity());
                st3.setInt(2, i.getProduct().getId());
                st3.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}