package com.jantsa.task.manager.TaskManager.controller;

import com.jantsa.task.manager.TaskManager.entity.Report;
import com.jantsa.task.manager.TaskManager.service.PersonalDashBoardImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/personal-dashboard")
public class PersonalDashboardController {

    @Autowired
    private PersonalDashBoardImp personalDashboard;

    @GetMapping
    public String getPersonalDashboard() {
        return "PersonalDashBoard";
    }

    @GetMapping("/reports/{personalId}")
    @ResponseBody
    public List<Report> getAssignedReports(@PathVariable Integer personalId) {
        return personalDashboard.findAssignedReports(personalId);
    }

    @PostMapping("/reports/{reportId}/accept")
    @ResponseBody
    public Report acceptReport(@PathVariable Integer reportId) {
        return personalDashboard.acceptReport(reportId);
    }

    @PostMapping("/reports/{reportId}/reject")
    @ResponseBody
    public Report rejectReport(@PathVariable Integer reportId) {
        return personalDashboard.rejectReport(reportId);
    }
}