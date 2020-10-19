package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class Util {

    public static Session createSession(){

            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/testbase")
                    .setProperty("dialect", "org.hibernate.dialect.MySQL5Dialect")
                    .setProperty("hibernate.connection.username", "root")
                    .setProperty("hibernate.connection.password", "1")
                    .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                    .addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory.openSession();
    }




}




