package com.jantsa.task.manager.TaskManager.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(path = "/admin-dashboard")
public class AdminDashboardController {

    @GetMapping
    public String getAdminDashboard() {
        return "/AdminDashBoard";
    }
}
