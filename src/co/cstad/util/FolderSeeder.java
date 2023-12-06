package co.cstad.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FolderSeeder {
    private final Connection connection;
    public FolderSeeder() {
        connection = DbUtil.getConnection();
    }
    public void folderSeeder(){
        String query = """
                    INSERT INTO categories (id, name) VALUES (1, 'Pop'), (2, 'Rock'), (3, 'Game')
                """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {}
    }
}
