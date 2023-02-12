package dao;
import model.Account;
import service.Crud;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    static String  jdbcURL = "jdbc:mysql://localhost:3306/case_module03.account";
    static String jdbcUsername = "root";
    static String jdbcPassword = "12345678";

    public Account checkLogin(String name, String password) throws ClassNotFoundException {
        String query = "select * from user where Email = ? and Pass = ?";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void createAccount(String name, String pass , String phone , String email, String address) throws ClassNotFoundException {
        String query = "insert into account(name,pass,phone , email , address) values (?,?,?,?,?)";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, pass);
            ps.setString(3, email);
            ps.setString(4,phone);
            ps.setString(5,address);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Account checkExistAccount(String name) throws ClassNotFoundException {
        String query = "select * from account where name = ?";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static List<Account> getListAccount() throws ClassNotFoundException {
        String query = "select * from customer";
        List<Account> accountsList = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                accountsList.add(new Account(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accountsList;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        AccountDAO accountDAO = new AccountDAO();
        List<Account> list = AccountDAO.getListAccount();
        for (Account c : list
        ) {
            System.out.println(c);
        }
    }
}
