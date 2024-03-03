package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

        private static ConnectDB instance;
        private Connection connection;

        private ConnectDB() {
            try {
                this.connection = DriverManager.getConnection
                        ("jdbc:postgresql://localhost:4321/postgres", "postgres", "4321");
            } catch (SQLException e) {
                throw new RuntimeException("Error connecting to the database", e);
            }
        }

        public static ConnectDB getInstance() {
            if (instance == null) {
                instance = new ConnectDB();
            }
            return instance;
        }

        public Connection getConnection() {
            return connection;
        }
    }

