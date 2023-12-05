package co.cstad.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategorySeeder {
    private final Connection connection;
    public CategorySeeder() {
        connection = DbUtil.getConnection();
    }
    public void categorySeeder(){
        String query = """
                    INSERT INTO categories (id, name) VALUES (1, 'Pop'), (2, 'Rock'), (3, 'Game')
                """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {}
    }
}
