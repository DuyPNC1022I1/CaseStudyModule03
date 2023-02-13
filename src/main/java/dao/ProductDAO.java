package dao;

import model.Brand;
import model.Product;
import service.Crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Crud<Product> {
    private BrandDAO brandDAO;
    private String jdbcUrl = "jdbc:mysql://localhost:3306/case_module03";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    public ProductDAO() {
        this.brandDAO = new BrandDAO();
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
            Connection c = getConnection();
            PreparedStatement pr = c.prepareStatement("select * from product");
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");
                String image = rs.getString("image");
                Brand brand = brandDAO.selectByName(rs.getString("brand"));

                products.add(new Product(id, name, price, quantity, description, image, brand));
            }
            c.close();
        }
        catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void create(Product product) {
        try {
            Connection c = getConnection();
            PreparedStatement pr = c.prepareStatement("insert into product(name, price, quantity, description, image, brand) values (?, ?, ?, ?, ?, ?)");
            pr.setString(1, product.getName());
            pr.setDouble(2, product.getPrice());
            pr.setInt(3, product.getQuantity());
            pr.setString(4, product.getDescription());
            pr.setString(5, product.getImage());
            pr.setString(6, product.getBrand().getName());

            pr.executeUpdate();
            c.close();
        }
        catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {
        try {
            Connection c = getConnection();
            PreparedStatement pr = c.prepareStatement("update product set name = ?, price =?, quantity =?, description =?, image =?, brand =? where id =?");
            pr.setString(1, product.getName());
            pr.setDouble(2, product.getPrice());
            pr.setInt(3, product.getQuantity());
            pr.setString(4, product.getDescription());
            pr.setString(5, product.getImage());
            pr.setString(6, product.getBrand().getName());
            pr.setInt(7, product.getId());

            pr.executeUpdate();
            c.close();
        } catch (SQLException| ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("delete from product where id =?");
            ps.setInt(1, id);
            ps.executeUpdate();
            c.close();
        } catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Product selectById(int id) {
        Product product = null;
        try {
            Connection connection = getConnection();
            PreparedStatement pr = connection.prepareStatement("select * from product where id = ?");
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                String name = rs.getString(2);
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");
                String image = rs.getString("image");
                Brand brand = brandDAO.selectByName(rs.getString("brand"));

                product = new Product(id, name, price, quantity, description, image, brand);
                connection.close();
            }
        }catch (SQLException| ClassNotFoundException e) {
            e.printStackTrace();
        }
        return product;
    }
}
