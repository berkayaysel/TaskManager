package com.jantsa.task.manager.TaskManager.controller;


import com.jantsa.task.manager.TaskManager.entity.Report;
import com.jantsa.task.manager.TaskManager.entity.User;
import com.jantsa.task.manager.TaskManager.service.BugReportServiceImlp;
import com.jantsa.task.manager.TaskManager.service.UserDashBoardImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(path = "/user-dashboard")
public class UserDashboardController {

    @Autowired
    UserDashBoardImpl userDashBoard;

    @Autowired
    BugReportServiceImlp bugReportServiceImlp;


    @GetMapping
    public String getUserDashboard() {

        return "/UserDashBoard";
    }

    @PostMapping(path = "/new")
    public ResponseEntity<Void> report(@RequestBody Report report){

        bugReportServiceImlp.save(report);

        return ResponseEntity.ok().build();
    }


    @GetMapping("/change-password")
    public String loadChangePasswordPage() {
        return "ChangePassword";
    }

    @PostMapping(path = "/change-password")
    public ResponseEntity<?> changePassword(@RequestBody Map<String, String> body) {
        Integer companyId = Integer.parseInt(body.get("companyId"));
        String oldPassword = body.get("oldPassword");
        String newPassword = body.get("newPassword");

        boolean success = userDashBoard.changePassword(companyId, oldPassword, newPassword);

        if (success) {
            return ResponseEntity.ok("Şifre başarıyla değiştirildi.");
        } else {
            return ResponseEntity.status(400).body("Mevcut şifre yanlış.");
        }
    }



}
