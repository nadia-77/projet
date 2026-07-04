package com.campus.visitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CampusVisitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusVisitorApplication.class, args);

        System.out.println("===========================================");
        System.out.println("  Application démarrée !");
        System.out.println("  Ouvrir : http://localhost:8080");
        System.out.println("  Admin  : admin@campus.ma / admin123");
        System.out.println("  Agent  : agent@campus.ma / agent123");
        System.out.println("===========================================");
    }
}
