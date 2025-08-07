package com.jantsa.task.manager.TaskManager.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/personal-dashboard")
public class PersonalDashboardController {

    @GetMapping
    public String getPersonalDashboard() {
        return "Personel Paneline Hoş Geldiniz.";
    }
}
