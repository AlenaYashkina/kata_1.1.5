package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

        try (SessionFactory sessionFactory = Util.getSessionFactory()) {
            userDaoHibernate.createUsersTable();
            userDaoHibernate.saveUser("Ivanov", "Ivan", (byte) 60);
            userDaoHibernate.saveUser("Petrov", "Petr", (byte) 30);
            userDaoHibernate.saveUser("Vasilev", "Vasiliy", (byte) 15);
            userDaoHibernate.saveUser("Nickolaev", "Nickolay", (byte) 45);
            userDaoHibernate.getAllUsers();
            userDaoHibernate.cleanUsersTable();
            userDaoHibernate.dropUsersTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
