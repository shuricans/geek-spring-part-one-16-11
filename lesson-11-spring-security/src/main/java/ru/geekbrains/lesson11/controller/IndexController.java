package ru.geekbrains.lesson11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public String index() {
        return "redirect:/product";
    }

    @GetMapping("/access_denied")
    public String upload() {
        return "access_denied";
    }
}
