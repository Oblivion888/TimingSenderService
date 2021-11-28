package com.soapcommandservice;


import java.util.List;


public class CommandService {

    private static UserService getService() {
        UserService userService = new UserServiceImplementationService()
                .getPort(UserService.class);
        return userService;
    }

    public static List<User> getAllUsers() {
        return getService().findAll();
    }
}
