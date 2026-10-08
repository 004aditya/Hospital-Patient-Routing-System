package com.kiranacademy.hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static String driver_class = "com.mysql.cj.jdbc.Driver";
    private static String database_url = "jdbc:mysql://localhost:3306/";
    private static String database_name = "batch_k_435";
    private static String database_username = "root";
    private static String database_password = "Root@123";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driver_class);
            connection = DriverManager.getConnection(
                database_url + database_name, 
                database_username, 
                database_password
            );
            System.out.println("Database connection established...");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found: " + e);
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e);
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e);
        }
    }
}
