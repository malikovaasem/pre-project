package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.connectDB().createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS User" +
                    "(id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(50) NOT NULL," +
                    "lastName VARCHAR(50)," +
                    "age TINYINT )");
        } catch (SQLException e) {
            System.out.println("Create table exception");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.connectDB().createStatement()) {
            statement.execute("DROP TABLE IF EXISTS User");
        } catch (SQLException e) {
            System.out.println("Drop table exception");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = Util.connectDB().createStatement()) {
            statement.executeUpdate(String.format("INSERT INTO User (name, lastName, age) " +
                    "VALUES(%s, %s, %d)", name, lastName, age));
            System.out.printf("User named %s added successfully\n", name);
        } catch (SQLException e) {
            System.out.println("User adding exception");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = Util.connectDB().createStatement()) {
            statement.executeUpdate(String.format("DELETE FROM User WHERE id = %d", id));
        } catch (SQLException e) {
            System.out.println("User removing exception");
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = Util.connectDB().createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM usersdb.User");
            while (rs.next()) {
                // какой ещё есть способ?
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                byte age = rs.getByte("age");

                User user = new User('\'' + name + '\'', '\'' + lastName + '\'', age);
                System.out.println(user.toString());
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Users get exception");
        }
        return null;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.connectDB().createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE user");
        } catch (SQLException e) {
            System.out.println("Trancate exception");
        }
    }
}
