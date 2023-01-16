package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class UserServiceImpl implements UserService {

    private Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    private UserDao userDao = new UserDaoJDBCImpl();

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        logger.log(Level.INFO, "User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        userDao.getAllUsers()
                .forEach(x -> logger.log(Level.INFO,
                        "ID: " + x.getId() + " | Name: "
                                + x.getName() + " | Last name: " + x.getLastName() + " | Age: " + x.getAge()));
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}