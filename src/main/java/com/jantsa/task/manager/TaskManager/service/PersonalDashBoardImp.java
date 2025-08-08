package com.jantsa.task.manager.TaskManager.service;

import com.jantsa.task.manager.TaskManager.entity.Report;
import com.jantsa.task.manager.TaskManager.enums.TaskStatus;
import com.jantsa.task.manager.TaskManager.repository.BugReportRepository;
import com.jantsa.task.manager.TaskManager.repository.PersonalDashBoardRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PersonalDashBoardImp implements PersonalDashBoard {

    @Autowired
    PersonalDashBoardRepository personalDashBoardRepository;

    @Autowired
    BugReportRepository bugReportRepository;

    @Override
    public List<Report> findAssignedReports(Integer personalId) {
        return bugReportRepository.findByPersonalId(personalId);
    }

    @Override
    @Transactional
    public Report acceptReport(Integer reportId) {
        Report report = bugReportRepository.findById(reportId)
                .orElseThrow(() -> new EntityNotFoundException("Report not found with ID: " + reportId));

        report.setStatus(TaskStatus.AKTIF);
        report.setKabul_date(LocalDateTime.now());

        return bugReportRepository.save(report);
    }

    @Override
    @Transactional
    public Report rejectReport(Integer reportId) {
        Report report = bugReportRepository.findById(reportId)
                .orElseThrow(() -> new EntityNotFoundException("Report not found with ID: " + reportId));

        report.setStatus(TaskStatus.IPTAL);
        report.setIptal_date(LocalDateTime.now());
        report.setPersonalId(null);
        report.setAtanma_date(null);

        return bugReportRepository.save(report);
    }
}