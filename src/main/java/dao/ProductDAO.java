package dao;

import model.Product;
import service.Crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Crud<Product> {
    private BrandDAO brandDAO;
    private String jdbcUrl = "jdbc:mysql://localhost:3306/case_module03?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    public ProductDAO() {
    }

    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        return connection;
    }

    @Override
    public List<Product> display() {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement pr = connection.prepareStatement("select * from product");
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");





            }
            connection.close();
        }

        catch (SQLException|ClassNotFoundException e) {

        }
        return products;
    }

    @Override
    public void creat(Product product) {

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(int id) {

    }

}
