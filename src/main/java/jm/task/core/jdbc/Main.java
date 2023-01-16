package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Ivanov", "Ivan", (byte) 60);
        userService.saveUser("Petrov", "Petr", (byte) 30);
        userService.saveUser("Vasilev", "Vasiliy", (byte) 15);
        userService.saveUser("Nickolaev", "Nickolay", (byte) 45);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
