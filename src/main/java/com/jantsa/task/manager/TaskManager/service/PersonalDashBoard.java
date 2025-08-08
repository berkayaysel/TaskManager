package com.jantsa.task.manager.TaskManager.service;

import com.jantsa.task.manager.TaskManager.entity.Report;
import java.util.List;

public interface PersonalDashBoard {
    List<Report> findAssignedReports(Integer personalId);
    Report acceptReport(Integer reportId);
    Report rejectReport(Integer reportId);
}