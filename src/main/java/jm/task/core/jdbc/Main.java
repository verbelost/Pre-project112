package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Егор", "Кузьмин", (byte) 26);
        userService.saveUser("Leonel", "Messi", (byte) 33);
        userService.saveUser("Johnny", "Depp", (byte) 56);
        userService.saveUser("Unknown", "Unknown", (byte) 100);
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
    }


}

