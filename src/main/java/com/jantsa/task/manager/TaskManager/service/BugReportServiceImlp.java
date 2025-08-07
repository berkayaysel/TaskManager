package com.jantsa.task.manager.TaskManager.service;

import com.jantsa.task.manager.TaskManager.entity.Report;
import com.jantsa.task.manager.TaskManager.repository.BugReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class BugReportServiceImlp implements BugReportService{

    @Autowired
    BugReportRepository bugReportRepository;


    @Override
    public Report save(Report report) {
        Report new_report = new Report();

        LocalDateTime now = LocalDateTime.now();
        new_report.setHeader(report.getHeader());
        new_report.setBody(report.getBody());
        new_report.setDate(now);
        new_report.setCompanyId(report.getcompanyId());
        bugReportRepository.save(new_report);
        return new_report;
    }
}
