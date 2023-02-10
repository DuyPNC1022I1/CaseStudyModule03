package dao;

import service.Crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Account implements Crud {
    private Account account;
    private String jdbcUrl = "jdbc:mysql://localhost:3306/case_module03?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    public Account() {
        this.account = new Account();
    }
    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        return connection;
    }



    @Override
    public List display() {
       return null;
    }

    @Override
    public void create(Object o) {

    }

    @Override
    public void create(Account account) {
        try {
            Connection acc= getConnection();
            PreparedStatement ac = acc.prepareStatement("insert into user(username, password , email , phone, address) values ( ?, ?, ?, ?, ?)");
            ac.setString(1, acc.getName());
            ac.setDouble(2, product.getPrice());
            ac.setInt(3, product.getQuantity());
            ac.setString(4, product.getDescription());
            ac.setString(5, product.getImage());
            ac.setString(6, product.getBrand().getName());

            pr.executeUpdate();
            c.close();
        }
        catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object o) {

    }

    @Override
    public void delete(int id) {

    }
}
