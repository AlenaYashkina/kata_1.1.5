package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String create = "CREATE TABLE IF NOT EXISTS `my_database`.`user` (\n" +
                "  `id` BIGINT(19) NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NULL,\n" +
                "  `lastName` VARCHAR(45) NULL,\n" +
                "  `age` TINYINT(3) NULL,\n" +
                "  PRIMARY KEY (`id`));";

        try (Statement statement = Util.getMySQLConnection().createStatement()) {
            statement.execute(create);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        String drop = "DROP TABLE IF EXISTS `my_database`.`user`";

        try (Statement statement = Util.getMySQLConnection().createStatement()) {
            statement.execute(drop);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String save = "INSERT INTO `my_database`.`user` (`name`, `lastName`, `age`) " +
                "VALUES ('" + name + "', '" + lastName + "', '" + age + "');";

        try (Statement statement = Util.getMySQLConnection().createStatement()) {
            statement.execute(save);
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void removeUserById(long id) {

        String removeByID = "DELETE FROM `my_database`.`user` WHERE `id` = " + id + ";";

        try (Statement statement = Util.getMySQLConnection().createStatement()) {
            statement.execute(removeByID);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        String getAll = "SELECT * FROM `my_database`.`user`;";
        List list = new LinkedList();

        try (Statement statement = Util.getMySQLConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAll);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        list.stream().forEach(System.out::println);
        return list;
    }

    public void cleanUsersTable() {

        String clean = "DELETE FROM `my_database`.`user`;";

        try (Statement statement = Util.getMySQLConnection().createStatement()) {
            statement.execute(clean);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}