package com.rfbsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringRestfull {
    public final static String DOMAIN = "http://localhost:8081";

    public static void main(String[] args) {
        SpringApplication.run(SpringRestfull.class, args);
    }



}



