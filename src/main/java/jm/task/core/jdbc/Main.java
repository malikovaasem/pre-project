package jm.task.core.jdbc;

import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl us = new UserServiceImpl();

        us.dropUsersTable();
        us.createUsersTable();
        us.saveUser("'John'", "'Johnson'", (byte) 38);
        us.saveUser("'Mickael'", "'Jackson'", (byte) 23);
        us.saveUser("'Robert'", "'Daune'", (byte) 25);
        us.saveUser("'Withney'", "'Huston'", (byte) 41);

        List<User> usersList = us.getAllUsers();

        for (User userr : usersList) {
            System.out.println(userr.toString());
        }

        us.cleanUsersTable();

        us.removeUserById(3);

    }
}
