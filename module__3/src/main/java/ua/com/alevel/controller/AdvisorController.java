package ua.com.alevel.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ua.com.alevel.model.User;

@ControllerAdvice
public class AdvisorController {
    @ModelAttribute("registerUser")
    public User getUserDefault() {
        return new User();
    }
}
