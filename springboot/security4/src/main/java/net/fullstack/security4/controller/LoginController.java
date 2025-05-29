package net.fullstack.security4.controller;

import net.fullstack.security4.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String loginGET(
        @ModelAttribute LoginForm loginForm
    ) {
        return "login";
    }
}