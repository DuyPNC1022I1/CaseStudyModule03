package dao;

import model.Account;
import service.Crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AccountDAO<E> implements Crud<Account> {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/case_module03?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        return connection;
    }

    public AccountDAO() {

    }

    @Override
    public List<Account> display() {
        return null;
    }

    @Override
    public void create(Account account) {
        try {
            Connection a = getConnection();
            PreparedStatement acc = a.prepareStatement("insert into user(username, password, phone, email, address) values (?, ?, ?, ?, ?)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update(Account account) {

    }

    @Override
    public void delete(int id) {

    }
}
