package com.trackingservice;

import com.soapcommandservice.CommandService;

import java.util.List;

public class SendReport {

    public static void main(String[] args) {
        List<String> allUsers = CommandService.getAllUsers();
        System.out.println(allUsers);
    }
}