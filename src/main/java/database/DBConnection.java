package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class responsible for managing the database connection.
 * Uses PostgreSQL as the database system.
 */
public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    // PostgreSQL connection details
    private static final String URL = "jdbc:postgresql://localhost:5432/restauracia";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    /**
     * Private constructor that initializes the database connection.
     */
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

    /**
     * Returns the single instance of DBConnection.
     *
     * @return DBConnection instance
     */
    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    /**
     * Returns the active database connection.
     *
     * @return Connection object
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Closes the database connection if it is open.
     */
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