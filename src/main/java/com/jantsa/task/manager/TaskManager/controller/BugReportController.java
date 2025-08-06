package com.jantsa.task.manager.TaskManager.controller;

import com.jantsa.task.manager.TaskManager.entity.Report;
import com.jantsa.task.manager.TaskManager.service.BugReportServiceImlp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/task")
public class BugReportController {

    @Autowired
    BugReportServiceImlp bugReportServiceImlp;


    @PostMapping(path = "/new")
    public Report report(@RequestBody Report report){
        return bugReportServiceImlp.report(report);
    }

}
