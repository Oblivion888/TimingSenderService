package com.soapcommandservice;

import java.util.ArrayList;
import java.util.List;

public class CommandService {

    private static UserService getService() {
        UserService userService = new UserServiceImplementationService()
                .getPort(UserService.class);
        return userService;
    }


    public static List<String> getAllUsers() {
        List<User> users = getService().findAll();
        List<String> stringUsers = new ArrayList<>();
        for (User u : users) {
            stringUsers.add(
                    u.getId() + "|"
                            + u.getUsername() + "|"
                            + u.getFirstName() + "|"
                            + u.getLastName() + "|"
                            + u.getRole() + "|"
                            + u.getGroup()
            );
        }

        return stringUsers;
    }


}
