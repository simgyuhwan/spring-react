package com.example.spa.controller;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Locale locale, Model model){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M년 d일 (E) a h시 m분 s초");
        String formatNow = now.format(formatter);
        model.addAttribute("serverTime", formatNow);
        return "home";
    }

}
