// UserDaoImpl.java
package co.cstad.dao;

import co.cstad.model.User;
import co.cstad.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private final Connection connection;
    public UserDaoImpl() {
        connection = DbUtil.getConnection();
    }

//    @Override
//    public User register(User user) {
//        String query = """
//                    INSERT INTO users (username, password) VALUES (?, ?)
//                """;
//        try(PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, user.getUsername());
//            statement.setString(2, user.getUsername());
//            int rowCount = statement.executeUpdate();
//            System.out.println("Register successfully!");
//            System.out.println(rowCount);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return user;
//    }
//
//    @Override
//    public User login(User user) {
//        String query = """
//                    SELECT username, password FROM users
//                        WHERE username = ? AND password = ?
//                """;
//        try (PreparedStatement statement = connection.prepareStatement(query)){
//            statement.setString(1, user.getUsername());
//            statement.setString(2, user.getPassword() );
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()){
//                user.setId(rs.getInt("id"));
//                user.setUsername(rs.getString("username"));
//                System.out.println("Login successfully!");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return user;
//    }
    @Override
    public User getUserByUsername(String username) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?")) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return null;
    }

    @Override
    public void addUser(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
