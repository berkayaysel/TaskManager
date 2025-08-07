package com.jantsa.task.manager.TaskManager.service;

import com.jantsa.task.manager.TaskManager.entity.Report;
import com.jantsa.task.manager.TaskManager.entity.User;

import java.util.List;
import java.util.Optional;

public interface AdminDashBoard {

    public List<User> getAllPersonal();
    public List <Report> findAll();
    Report rejectReport(Integer reportId);
    Optional<Report> findById(Integer id);
    Report completeReport(Integer reportId, Integer personalId);

}
