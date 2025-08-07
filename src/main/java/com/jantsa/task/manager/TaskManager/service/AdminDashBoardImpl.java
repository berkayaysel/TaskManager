package com.jantsa.task.manager.TaskManager.service;


import com.jantsa.task.manager.TaskManager.entity.Report;
import com.jantsa.task.manager.TaskManager.entity.User;
import com.jantsa.task.manager.TaskManager.enums.UserRole;
import com.jantsa.task.manager.TaskManager.repository.AdminDashBoardRepository;
import com.jantsa.task.manager.TaskManager.repository.BugReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminDashBoardImpl implements AdminDashBoard {

    @Autowired
    AdminDashBoardRepository adminDashBoardRepository;

    @Autowired
    BugReportRepository bugReportRepository;

    @Override
    public List<User> getAllPersonal() {
        return adminDashBoardRepository.findByUserRole(UserRole.PERSONEL);
    }

    @Override
    public List<Report> findAll() {
        return bugReportRepository.findAll();
    }

}
