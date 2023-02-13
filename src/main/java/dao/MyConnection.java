package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    //phương thức để lấy ra đối tương Connection nhằm kết nối với DB
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //đường dẫn đến DB muốn thao tác, tài khoản và mật khẩu
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/case_module03?useSSL=false",
                    "root", "123456");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}