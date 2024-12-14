package db;
import animals.Animal;
import pets.Cat;
import pets.Dog;
import birds.Duck;


import javax.xml.transform.Result;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Statement;



public class DatabaseManager {

/*    private static final String PROPERTIES_FILE = "src/main/resources/SQLSettings.properties";
    private static String url;
    private static String username;
    private static String password;

    // Загрузка настроек подключения из файла db.properties
    static {
        try {
            assert url != null;
            try (Connection connection = DriverManager.getConnection(url, username, password);



                     Statement statement = connection.createStatement()) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS Animals (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "name VARCHAR(50), " +
                        "age INT, " +
                        "weight DOUBLE, " +
                        "color VARCHAR(50), " +
                        "type VARCHAR(20))");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для сохранения животного в базу данных
    public static void saveAnimal(Animal animal, String type) {
        String query = "INSERT INTO Animals (name, age, weight, color, type) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, animal.getName());
            preparedStatement.setInt(2, animal.getAge());
            preparedStatement.setDouble(3, animal.getWeight());
            preparedStatement.setString(4, animal.getColor());
            preparedStatement.setString(5, type);

            preparedStatement.executeUpdate();
            System.out.println("Животное добавлено в базу данных.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    // Метод для получения списка животных из базы данных
    private static List<Animal> animals = new ArrayList<>();

    public static void saveAnimal(Animal animal) {
        // Сохраняем животное в список (или базу данных)
        animals.add(animal);
    }

    public static void listAnimals(String type) {
        // Выводим все животные, или фильтруем по типу
        for (Animal animal : animals) {
            if (type == null || animal.getClass().getSimpleName().toLowerCase().equals(type)) {
                System.out.println(animal);
            }
        }
    }



    // Метод для обновления информации о животном
    public static void updateAnimal(int id, Animal animal, String type) {
        String query = "UPDATE Animals SET name = ?, age = ?, weight = ?, color = ?, type = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, animal.getName());
            preparedStatement.setInt(2, animal.getAge());
            preparedStatement.setDouble(3, animal.getWeight());
            preparedStatement.setString(4, animal.getColor());
            preparedStatement.setString(5, type);
            preparedStatement.setInt(6, id);

            preparedStatement.executeUpdate();
            System.out.println("Животное обновлено в базе данных.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}*/


    private static Connection connection;

    static {
        try {
            Properties properties = new Properties();
            FileInputStream inputStream = new FileInputStream("resources/SQLSettings.properties");
            properties.load(inputStream);
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            assert url != null : "URL для подключения не может быть null";

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Подключение к базе данных установлено.");
            createTable();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка подключения к базе данных.");
        }
    }

    // Создание таблицы animals, если она не существует
    private static void createTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS animals (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "age INT, " +
                "weight DOUBLE, " +
                "color VARCHAR(50), " +
                "type VARCHAR(50))";

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(createTableSQL);
            System.out.println("Таблица 'animals' создана или уже существует.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка при создании таблицы.");
        }
    }

    // Пример метода для выполнения SQL-запроса (например, вывод животных)
// Метод для получения списка животных из базы данных
    private static List<Animal> animals = new ArrayList<>();

    public static void saveAnimal(Animal animal) {
        // Сохраняем животное в список (или базу данных)
        animals.add(animal);
    }

    public static void listAnimals(String type) {
        // Выводим все животные, или фильтруем по типу
        for (Animal animal : animals) {
            if (type == null || animal.getClass().getSimpleName().toLowerCase().equals(type)) {
                System.out.println(animal);
            }
        }
    }

    // Закрытие подключения
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Подключение закрыто.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Пример метода для сохранения животного в базе данных
    public static void saveAnimal(Animal animal, String type) {
        String insertSQL = "INSERT INTO animals (name, age, weight, color, type) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setInt(2, animal.getAge());
            preparedStatement.setDouble(3, animal.getWeight());
            preparedStatement.setString(4, animal.getColor());
            preparedStatement.setString(5, type);

            preparedStatement.executeUpdate();
            System.out.println("Животное добавлено в базу данных.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Пример метода для обновления животного в базе данных
    public static void updateAnimal(int id, Animal animal, String type) {
        String updateSQL = "UPDATE animals SET name = ?, age = ?, weight = ?, color = ?, type = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setInt(2, animal.getAge());
            preparedStatement.setDouble(3, animal.getWeight());
            preparedStatement.setString(4, animal.getColor());
            preparedStatement.setString(5, type);
            preparedStatement.setInt(6, id);

            preparedStatement.executeUpdate();
            System.out.println("Животное обновлено в базе данных.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}