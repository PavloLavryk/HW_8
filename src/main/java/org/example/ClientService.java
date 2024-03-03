package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private final Connection connection;
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 1000;

    public ClientService(Connection connection) {
        this.connection = connection;
    }

    public class Client {
        long id;
        String name;
    }

    private void validateName(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Client name must be between " + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH + " characters.");
        }
    }

    public long create(String name) throws SQLException {
        validateName(name);
        String sql = "INSERT INTO client (name) VALUES (?) RETURNING id";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            } else {
                throw new SQLException("Creating client failed, no ID obtained.");
            }
        }
    }

    public String getById(long id) throws SQLException {
        String sql = "SELECT name FROM client WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("name");
            } else {
                throw new SQLException("No client found with id: " + id);
            }
        }
    }

    public void setName(long id, String name) throws SQLException {
        validateName(name);
        String sql = "UPDATE client SET name = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }

    public void deleteById(long id) throws SQLException {
        String sql = "DELETE FROM client WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Client> listAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT id, name FROM client";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Client client = new Client();
                client.id = rs.getLong("id");
                client.name = rs.getString("name");
                clients.add(client);
            }
        }
        return clients;
    }

}
