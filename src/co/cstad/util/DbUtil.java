package co.cstad.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static Connection connection;
    public static Connection getConnection(){
        if(connection == null){
            try {
                try {
                    Class.forName("org.postgresql.Driver");
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                String password = "092728853";
                String username = "postgres";
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/media-downloader", username, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
