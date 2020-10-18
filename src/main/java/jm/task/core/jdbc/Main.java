package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Vova", "Boyko", (byte) 27);
        userService.saveUser("Ivan", "Ivanov", (byte) 19);
        userService.saveUser("Anna", "Maximova", (byte) 20);
        userService.saveUser("Masha", "Petrova", (byte) 15);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}