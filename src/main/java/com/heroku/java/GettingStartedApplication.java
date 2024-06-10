package com.heroku.java;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@SpringBootApplication
public class GettingStartedApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(GettingStartedApplication.class, args);
    }
}

