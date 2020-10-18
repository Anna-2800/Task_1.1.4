package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import java.util.Iterator;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

        Session session = Util.createSession();

        try {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS user (Id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                    "name VARCHAR(20) NOT NULL, " +
                    "lastname VARCHAR(20) NOT NULL, " +
                    "age INT NOT NULL)").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            } finally {
                    session.getSessionFactory().close();
            }
        }


    @Override
    public void dropUsersTable() {

        Session session = Util.createSession();

        try {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS user ").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.getSessionFactory().close();
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {

        Session session = Util.createSession();

            Transaction transaction = session.beginTransaction();

            User user = new User(name, lastName, age);
            session.save(user);
            System.out.println("User с именем " + name + " добавлен в базу данных");

            transaction.commit();
            session.getSessionFactory().close();
    }


    @Override
    public void removeUserById(long id) {

        Session session = null;
        User user = new User();

        try {
            session = Util.createSession();
            Transaction transaction = session.beginTransaction();

            user.setId(id);
            session.delete(user);
            transaction.commit();

        } catch (Exception e){
            System.out.println("id is not exists");
        } finally {
            session.getSessionFactory().close();
        }
    }


    @Override
    public List<User> getAllUsers() {


        Session session = Util.createSession();
        Transaction transaction = session.beginTransaction();

        List<User> users = session.createCriteria(User.class).list();

        for (Iterator<User> it = users.iterator(); it.hasNext();) {
            User user = (User) it.next();
            System.out.println(user.toString());
        }

        transaction.commit();
        session.getSessionFactory().close();

        return users;
    }


    @Override
    public void cleanUsersTable() {

        Session session = Util.createSession();
        Transaction transaction = session.beginTransaction();

        List<User> users = session.createCriteria(User.class).list();
        for (Object obj : users){
            session.delete(obj);
        }

        transaction.commit();
        session.getSessionFactory().close();
    }
}
