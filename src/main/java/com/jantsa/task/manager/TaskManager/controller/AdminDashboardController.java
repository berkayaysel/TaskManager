package com.jantsa.task.manager.TaskManager.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin-dashboard")
public class AdminDashboardController {

    @GetMapping
    public String getAdminDashboard() {
        return "Admin Paneline Hoş Geldiniz.";
    }
}
