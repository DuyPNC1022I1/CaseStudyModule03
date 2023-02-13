package dao;
import model.Account;

import java.sql.*;

public class AccountDAO {
    private final Connection conn;
    private final String ACCOUNT_LOGIN = "select * from account where name = ? and pass = ? ";
    private final String CREATE_ACCOUNT = "insert into account(name , pass , phone , email , address ) values ( ? , ? , ? , ? , ? )";

    public AccountDAO() {
        conn = MyConnection.getConnection();
    }
    public Account checkLogin(String name, String password) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            PreparedStatement ps = conn.prepareStatement(ACCOUNT_LOGIN);
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getInt(7));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void create(Account account) {
        try (PreparedStatement pr = conn.prepareStatement(CREATE_ACCOUNT)) {
            pr.setString(1, account.getName());
            pr.setString(2, account.getPass());
            pr.setString(3, account.getPhone());
            pr.setString(4, account.getEmail());
            pr.setString(5, account.getAddress());
            pr.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}