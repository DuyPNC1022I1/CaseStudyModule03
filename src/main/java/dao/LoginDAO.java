package dao;

import java.sql.*;

public class LoginDAO {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        return connection;
    }
    public Account checkLogin(String user,String pass){
        try {
            String query = "select * from account where username = ? and password = ?";
            connection = new DBContext().getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,user);
            preparedStatement.setString(2,pass);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Account account = new Account(resultSet.getString(user),resultSet.getString(pass));
                return  account;
            }

        } catch (Exception e) {

        }
        return null;
    }
}
