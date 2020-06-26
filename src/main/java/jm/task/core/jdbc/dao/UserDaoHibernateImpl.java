package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Connection connection;
    private Statement statement;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
                                                //SQL
    }

    @Override
    public void dropUsersTable() {
                                                //SQL
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
