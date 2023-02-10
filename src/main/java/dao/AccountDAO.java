package dao;
import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AccountDAO  {
    private List<Account> accounts;
    private final Connection connection;
    private final String INSERT_NEWACCOUNT = "insert into account( name , pass , phone ,  email ,address ) values (?, ?,?,?,?)";
    private final String UPDATE_ACCOUNT = "update account set pass = ? where email = ?";

    public AccountDAO() {
        connection =MyConnection.getConnection();
    }
    public void create(Account account) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEWACCOUNT)) {
            preparedStatement.setString(1, account.getName());
            preparedStatement.setString(2, account.getPass());
            preparedStatement.setString(3, account.getPhone());
            preparedStatement.setString(4, account.getEmail());
            preparedStatement.setString(5, account.getAddress());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void update(Account account) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT)) {
            preparedStatement.setString(2 , account.getPass());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
