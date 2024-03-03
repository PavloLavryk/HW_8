package org.example;

import org.flywaydb.core.Flyway;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // Отримуємо існуюче з'єднання з базою даних
        Connection connection = ConnectDB.getInstance().getConnection();

        ClientService clientService = new ClientService(connection);
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:4321/postgres", "postgres", "4321")
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .load();

        // Застосовуємо міграції
        flyway.migrate();

        // Створюємо нового клієнта
        try {
            long id = clientService.create("New Client");
            System.out.println("Created client with id: " + id);

            // Отримуємо ім'я клієнта за id
            String name = clientService.getById(id);
            System.out.println("Client name: " + name);

           // Змінюємо ім'я клієнта
            clientService.setName(id, "Updated Client");
            name = clientService.getById(id);
            System.out.println("Updated client name: " + name.toString());

//            // Видаляємо клієнта
//            clientService.deleteById(id);
//            System.out.println("Deleted client with id: " + id);

            // Виводимо список всіх клієнтів
            System.out.println("All clients: " + clientService.listAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
