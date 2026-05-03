package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    // PostgreSQL connection details
    private static final String URL = "jdbc:postgresql://localhost:5432/restauracia";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private DBConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("PostgreSQL pripojenie úspešné!");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL ovládač sa nenašiel!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Chyba pri pripojení k databáze!");
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Pripojenie uzavreté");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
