package com.jantsa.task.manager.TaskManager.controller;


import com.jantsa.task.manager.TaskManager.entity.Report;
import com.jantsa.task.manager.TaskManager.entity.User;
import com.jantsa.task.manager.TaskManager.service.AdminDashBoardImpl;
import com.jantsa.task.manager.TaskManager.service.UserDashBoardImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping(path = "/admin-dashboard")
public class AdminDashboardController {

    @Autowired
    AdminDashBoardImpl adminDashBoard;

    @GetMapping
    public String getAdminDashboard() {

        return "/AdminDashBoard";
    }


    @GetMapping(path = "/all-users")
    @ResponseBody
    public List<User> getAllPersonal(){
        return adminDashBoard.getAllPersonal();
    }

    @GetMapping(path = "/all-reports")
    @ResponseBody
    public List<Report> findAll(){
        return adminDashBoard.findAll();
    }




}
