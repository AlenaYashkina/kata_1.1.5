package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable() {

        String create = "CREATE TABLE IF NOT EXISTS `my_database`.`user` (\n" +
                "  `id` BIGINT(19) NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NULL,\n" +
                "  `lastName` VARCHAR(45) NULL,\n" +
                "  `age` TINYINT(3) NULL,\n" +
                "  PRIMARY KEY (`id`));";

        try (PreparedStatement statement = Util.getMySQLConnection().prepareStatement(create)) {
            statement.executeUpdate(create);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        String drop = "DROP TABLE IF EXISTS `my_database`.`user`";

        try (PreparedStatement statement = Util.getMySQLConnection().prepareStatement(drop)) {
            statement.executeUpdate(drop);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String save = ("INSERT into `my_database`.`user` (name, lastName, age) VALUES (?,?,?)");

        try (PreparedStatement statement = Util.getMySQLConnection()
                .prepareStatement(save)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        String removeByID = "DELETE FROM `my_database`.`user` WHERE `id` = ?";

        try (PreparedStatement statement = Util.getMySQLConnection().prepareStatement(removeByID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        String getAll = "SELECT * FROM `my_database`.`user`;";
        List list = new LinkedList();

        try (PreparedStatement statement = Util.getMySQLConnection().prepareStatement(getAll)) {
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

        return list;
    }

    public void cleanUsersTable() {

        String clean = "DELETE FROM `my_database`.`user`;";

        try (PreparedStatement statement = Util.getMySQLConnection().prepareStatement(clean)) {
            statement.executeUpdate(clean);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}