package co.cstad.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    public static Connection getConnection() throws SQLException {
        // Provide your database connection details
        String url = "jdbc:postgresql://localhost:5432/mydatabase";
        String username = "postgres";
        String password = "Lymann-2";

        return DriverManager.getConnection(url, username, password);
    }
}
