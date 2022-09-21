package com.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        UserService userService = (UserService) context.getBean("userService");
        System.out.println(userService.notificationService.imprimirSaludo());
    }
}
