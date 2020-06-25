package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;
    private Statement statement;

    public UserDaoJDBCImpl() {
        try {
            connection = Util.getConnection();
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection failed...");
            System.out.println(e);
        }
    }

    public void createUsersTable() {
        String sqlCommand = "CREATE TABLE users " +
                "(Id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                "Name VARCHAR(20) NOT NULL, " +
                "lastName VARCHAR(20) NOT NULL, " +
                "Age INTEGER NOT NULL)";
        try {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            System.out.println("Creating table is failed...");
        }
    }

    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE users";
        try {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            System.out.println("Dropping table is failed...");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlCommand = "INSERT users (name, lastName, Age) Values (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.execute();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Saving is failed...");
        }
    }

    public void removeUserById(long id) {
        try {
            String sqlCommand = "DELETE FROM users WHERE Id = ";
            statement.executeUpdate(sqlCommand + id);
        } catch (SQLException e) {
            System.out.println("Removing user is failed...");
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (true) {
                try {
                    if (!resultSet.next()) break;
                } catch (SQLException e) {
                    System.out.println("Getting all users is failed...");
                }
                String name = resultSet.getString("Name");
                String lastName = resultSet.getString("lastName");
                Byte age = resultSet.getByte("Age");
                User user = new User(name, lastName, age);
                user.setId(resultSet.getLong("Id"));
                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Getting all users is failed...");
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            String sqlCommand = "DELETE FROM users";
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            System.out.println("Cleaning table is failed...");
        }

    }
}
