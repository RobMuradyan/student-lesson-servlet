package com.example.studentlessonservlet.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {
    private static DBConnectionProvider dbConectionProvider = null;
    private Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/lesson_student";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";


    private DBConnectionProvider() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static DBConnectionProvider getinstsance() {
        if (dbConectionProvider == null) {
            dbConectionProvider = new DBConnectionProvider();
        }
        return dbConectionProvider;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {

                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}