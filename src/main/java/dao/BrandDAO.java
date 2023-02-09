package dao;

import model.Brand;
import service.Crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandDAO<E> implements Crud<Brand> {

    private String jdbcUrl = "jdbc:mysql://localhost:3306/case_module03?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";
    public BrandDAO() {
    }

    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        return connection;
    }
    public Brand selectByName(String name) {
        Brand brand = null;
        try {
            Connection connection = getConnection();
            PreparedStatement pr = connection.prepareStatement("select * from brand where name = ? ");
            pr.setString(1, name);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                name = rs.getString("name");
                int id = rs.getInt("id");
                brand = new Brand(id, name);
            }
            connection.close();
        }
        catch (SQLException| ClassNotFoundException e) {
            e.printStackTrace();
        }
        return brand;
    }

    @Override
    public List<Brand> display() {
        List<Brand> brands = new ArrayList<>();
        try {
            Connection c = getConnection();
            PreparedStatement pr = c.prepareStatement("select * from brand");
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                brands.add(new Brand(id, name));
            }
            c.close();
        }catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        return brands;
    }

    @Override
    public void create(Brand brand) {
    }

    @Override
    public void update(Brand brand) {

    }

    @Override
    public void delete(int id) {
    }
}
