package com.heroku.java.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.heroku.java.model.customer;

@Controller
public class CustomerController {

    private final DataSource dataSource;

    @Autowired
    public CustomerController(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @GetMapping("/customerRegister")
    public String customerRegister(Model model) {
        model.addAttribute("customerRegister,new customer()");
        return "account/customerRegister";
    }
    
    @PostMapping("/customerRegister")
    public String customerRegister(@ModelAttribute("customerRegister") customer customer,Model model) {
    
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

}


