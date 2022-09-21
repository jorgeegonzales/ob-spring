package com.service;

import org.springframework.stereotype.Component;

@Component
public class NotificationService {

    public NotificationService() {
        System.out.println("Ejecutando constructor de NotificationService");
    }

    public String imprimirSaludo(){
        return "Hola como estas";
    }
}
