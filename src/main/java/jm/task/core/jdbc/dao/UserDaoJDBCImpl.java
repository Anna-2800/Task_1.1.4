//package jm.task.core.jdbc.dao;
//
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDaoJDBCImpl implements UserDao {
//
//    Util util =  new Util();
//
//    public UserDaoJDBCImpl() {
//    }
//
//    public void createUsersTable() {
//        util.connection();
//
//        try (Statement statement = util.conn.createStatement()) {
//            util.conn.setAutoCommit(false);
//            String table = "CREATE TABLE IF NOT EXISTS base (Id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
//                    "name VARCHAR(20) NOT NULL, " +
//                    "lastname VARCHAR(20) NOT NULL, " +
//                    "age INT NOT NULL)";
//            statement.executeUpdate(table);
//            util.conn.commit();
//        } catch (SQLException t) {
//            t.printStackTrace();
//            try {
//                util.conn.rollback();
//            } catch (SQLException r) {
//                r.printStackTrace();
//            } finally {
//                try {
//                    util.conn.setAutoCommit(true);
//                } catch (SQLException y) {
//                    y.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public void dropUsersTable() {
//        util.connection();
//
//        try (Statement statement = util.conn.createStatement()){
//            util.conn.setAutoCommit(false);
//            String tableDrop = "DROP TABLE if EXISTS base";
//            statement.executeUpdate(tableDrop);
//            util.conn.commit();
//        } catch (SQLException t) {
//            t.printStackTrace();
//            try {
//                util.conn.rollback();
//            } catch (SQLException r) {
//                r.printStackTrace();
//            } finally {
//                try {
//                    util.conn.setAutoCommit(true);
//                } catch (SQLException y) {
//                    y.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public void saveUser(String name, String lastName, byte age) {
//
//        try (PreparedStatement statement = util.conn.prepareStatement ("INSERT INTO base (name, lastname, age) VALUES (?, ?, ?)")) {
//            util.connection();
//            util.conn.setAutoCommit(false);
//            statement.setString(1, name);
//            statement.setString(2, lastName);
//            statement.setByte(3, age);
//            statement.executeUpdate();
//            System.out.println("User с именем " + name + " добавлен в базу данных");
//            util.conn.commit();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            try {
//                util.conn.rollback();
//            } catch (SQLException r) {
//                r.printStackTrace();
//            } finally {
//                try {
//                    util.conn.setAutoCommit(true);
//                } catch (SQLException y) {
//                    y.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public void removeUserById(long id) {
//
//        try (PreparedStatement statement = util.conn.prepareStatement("DELETE FROM base WHERE Id = ?")) {
//            util.connection();
//            util.conn.setAutoCommit(false);
//            statement.setLong(1, id);
//            statement.executeUpdate();
//            util.conn.commit();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            try {
//                util.conn.rollback();
//            } catch (SQLException r) {
//                r.printStackTrace();
//            } finally {
//                try {
//                    util.conn.setAutoCommit(true);
//                } catch (SQLException y) {
//                    y.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public List<User> getAllUsers() {
//
//        List<User> results = new ArrayList<>();
//        String sql = "SELECT * FROM base";
//
//        try (PreparedStatement statement = util.conn.prepareStatement(sql)){
//            util.connection();
//            util.conn.setAutoCommit(false);
//            ResultSet resultSet = statement.executeQuery(sql);
//            while (resultSet.next()){
//                User user = new User();
//                user.setId(resultSet.getLong(1));
//                user.setName(resultSet.getString(2));
//                user.setLastName(resultSet.getString(3));
//                user.setAge(resultSet.getByte(4));
//                System.out.println(user.toString());
//                results.add(user);
//            }
//            util.conn.commit();
//            util.conn.setAutoCommit(true);
//        } catch (SQLException t) {
//            t.printStackTrace();
//            try {
//                util.conn.rollback();
//            } catch (SQLException r) {
//                r.printStackTrace();
//            } finally {
//                try {
//                    util.conn.setAutoCommit(true);
//                } catch (SQLException y) {
//                    y.printStackTrace();
//                }
//            }
//        }
//        return results;
//    }
//
//    public void cleanUsersTable() {
//
//        try (Statement statement = util.conn.createStatement()){
//            util.connection();
//            util.conn.setAutoCommit(false);
//            String tableClean = "TRUNCATE TABLE base ";
//            statement.executeUpdate(tableClean);
//            util.conn.commit();
//            util.conn.setAutoCommit(true);
//        } catch (SQLException t) {
//            t.printStackTrace();
//            try {
//                util.conn.rollback();
//            } catch (SQLException r) {
//                r.printStackTrace();
//            } finally {
//                try {
//                    util.conn.setAutoCommit(true);
//                } catch (SQLException y) {
//                    y.printStackTrace();
//                }
//            }
//        }
//    }
//}
