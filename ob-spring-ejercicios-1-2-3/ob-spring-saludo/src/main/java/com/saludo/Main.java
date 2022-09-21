package com.saludo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        Saludo saludo = (Saludo) ctx.getBean("saludo");

        System.out.println(saludo.imprimirSaludo());

    }
}
