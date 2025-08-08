package com.jantsa.task.manager.TaskManager.controller;


import com.jantsa.task.manager.TaskManager.entity.Report;
import com.jantsa.task.manager.TaskManager.entity.User;
import com.jantsa.task.manager.TaskManager.repository.BugReportRepository;
import com.jantsa.task.manager.TaskManager.service.AdminDashBoardImpl;
import com.jantsa.task.manager.TaskManager.service.UserDashBoardImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/reports/{id}/reject")
    @ResponseBody
    public Report rejectReport(@PathVariable Integer id) {
        return adminDashBoard.rejectReport(id);
    }

    @PostMapping("/reports/{id}/complete")
    @ResponseBody
    public Report completeReport(
            @PathVariable Integer id,
            @RequestParam("actorId") Integer actorId) {
        return adminDashBoard.completeReport(id, actorId);
    }

}
