package com.jantsa.task.manager.TaskManager.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user-dashboard")
public class UserDashboardController {

    @GetMapping
    public String getUserDashboard() {
        return "Kullanıcı Paneline Hoş Geldiniz.";
    }
}
