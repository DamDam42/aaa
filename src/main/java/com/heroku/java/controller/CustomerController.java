package com.heroku.java.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.heroku.java.model.customer;
@SpringBootApplication
@Controller
public class CustomerController {

    private final DataSource dataSource;

    @Autowired
    public CustomerController(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("customerRegister", new customer());
        return "/customerRegister";
    }
    @PostMapping("/customerRegister")
    public String customerRegister(@ModelAttribute customer customer,Model model) {
        model.addAttribute("customerRegister", new customer());
        try (Connection connection = dataSource.getConnection()){
            String sql = "INSERT INTO public.customer(custname, custpassword, custemail, custphonenum, custaddress) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getCustName());
            statement.setString(2, customer.getCustPassword());
            statement.setString(3, customer.getCustEmail());
            statement.setString(4, customer.getCustPhoneNum());
            statement.setString(5, customer.getCustAddress());
            statement.executeUpdate();
            System.out.println("Name: " + customer.getCustName());
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/index";
        }
        return "redirect:/account";
    } 

     @GetMapping("/database")
    String database(Map<String, Object> model) {
        try (Connection connection = dataSource.getConnection()) {
            final var statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
            statement.executeUpdate("INSERT INTO ticks VALUES (now())");

            final var resultSet = statement.executeQuery("SELECT tick FROM ticks");
            final var output = new ArrayList<>();
            while (resultSet.next()) {
                output.add("Read from DB: " + resultSet.getTimestamp("tick"));
            }

            model.put("records", output);
            return "database";

        } catch (Throwable t) {
            model.put("message", t.getMessage());
            return "error";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomerController.class, args);
    }
}

