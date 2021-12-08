package com.enbaev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/service")
public class RegisterController {
    @GetMapping
    public String create() {
        return "registr";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String output(HttpServletRequest request, Model model) {
        String name = request.getParameter("FIRST_NAME");
        String lastName = request.getParameter("LAST_NAME");
        String user_gender = request.getParameter("user_gender");
        String password = request.getParameter("USER_PASSWORD");
        String userAgree = request.getParameter("check");

        if (!CheckForRegistration.checkNullForRegistration(name, lastName, user_gender, password)) {
            model.addAttribute("error", "Lines cannot be empty!");
            return "error";
        } else if (!CheckForRegistration.checkCorrectPassword(password)) {
            model.addAttribute("error", "Password cannot be less than 6 characters");
            return "error";
        } else if (CheckForRegistration.checkAgree(userAgree)) {
            model.addAttribute("error", "You have not confirmed the processing of data");
            return "error";
        } else if (!CheckForRegistration.checkNameLastNameContainsNumber(name, lastName)) {
            model.addAttribute("error", "First and last name cannot contain numbers");
            return "error";
        } else {
            model.addAttribute("FIRST_NAME", name);
            model.addAttribute("LAST_NAME", lastName);
            return "success";
        }
    }
}