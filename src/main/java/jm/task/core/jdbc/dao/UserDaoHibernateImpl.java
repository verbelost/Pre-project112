package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory;


    public UserDaoHibernateImpl() {
        sessionFactory = Util.createSessionFactory();
    }


    @Override
    public void createUsersTable() {
        String sqlCommand = "CREATE TABLE IF NOT EXISTS users " +
                "(Id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                "Name VARCHAR(20) NOT NULL, " +
                "lastName VARCHAR(20) NOT NULL, " +
                "Age INTEGER NOT NULL)";
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        session.createSQLQuery(sqlCommand).executeUpdate();
        trx.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE users";
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        session.createSQLQuery(sqlCommand);
        trx.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        session.save(new User(name, lastName, age));
        trx.commit();
        session.close();
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        User user = (User)session.load(User.class, id);
        session.delete(user);
        trx.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        List<User> list = (List<User>) criteria.list();
        trx.commit();
        session.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        trx.commit();
        session.close();
    }
}
