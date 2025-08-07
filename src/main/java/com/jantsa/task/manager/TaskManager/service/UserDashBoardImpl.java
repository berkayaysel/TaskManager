package com.jantsa.task.manager.TaskManager.service;


import com.jantsa.task.manager.TaskManager.dto.BugReportRequestDto;
import com.jantsa.task.manager.TaskManager.entity.Report;
import com.jantsa.task.manager.TaskManager.entity.User;
import com.jantsa.task.manager.TaskManager.repository.BugReportRepository;
import com.jantsa.task.manager.TaskManager.repository.UserDashBoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserDashBoardImpl implements UserDashBoard{

    @Autowired
    UserDashBoardRepository userDashBoardRepository;

    @Autowired
    BugReportRepository bugReportRepository;


    @Override
    public boolean changePassword(Integer companyId, String oldPassword, String newPassword) {
        Optional<User> userOpt = userDashBoardRepository.findByCompanyId(companyId);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(oldPassword)) {
                user.setPassword(newPassword);
                userDashBoardRepository.save(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public void save(BugReportRequestDto bugReportRequestDto) {
        Report new_report = new Report();

        LocalDateTime now = LocalDateTime.now();
        new_report.setHeader(bugReportRequestDto.getHeader());
        new_report.setBody(bugReportRequestDto.getBody());
        new_report.setCompanyId(bugReportRequestDto.getCompanyId());
        new_report.setBitis_date(null);
        new_report.setKabul_date(null);
        new_report.setAtanma_date(null);
        new_report.setTalep_date(now);
        new_report.setIptal_date(null);

        bugReportRepository.save(new_report);

    }
}

