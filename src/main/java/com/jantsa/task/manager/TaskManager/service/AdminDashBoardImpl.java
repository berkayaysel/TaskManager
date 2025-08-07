package com.jantsa.task.manager.TaskManager.service;


import com.jantsa.task.manager.TaskManager.entity.Report;
import com.jantsa.task.manager.TaskManager.entity.User;
import com.jantsa.task.manager.TaskManager.enums.TaskStatus;
import com.jantsa.task.manager.TaskManager.enums.UserRole;
import com.jantsa.task.manager.TaskManager.repository.AdminDashBoardRepository;
import com.jantsa.task.manager.TaskManager.repository.BugReportRepository;
import com.jantsa.task.manager.TaskManager.repository.UserDashBoardRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminDashBoardImpl implements AdminDashBoard {

    @Autowired
    AdminDashBoardRepository adminDashBoardRepository;

    @Autowired
    BugReportRepository bugReportRepository;

    @Autowired
    UserDashBoardRepository userDashBoardRepository;

    @Override
    public List<User> getAllPersonal() {
        return adminDashBoardRepository.findByUserRole(UserRole.PERSONEL);
    }

    @Override
    public List<Report> findAll() {
        return bugReportRepository.findAll();
    }

    @Override
    @Transactional
    public Report rejectReport(Integer reportId) {
        Report rpt = bugReportRepository.findById(reportId)
                .orElseThrow(() -> new EntityNotFoundException("Report not found: " + reportId));

        // 1. Durumu IPTAL olarak ayarla
        rpt.setStatus(TaskStatus.IPTAL);

        // 2. Reddedilme tarihini kaydet
        rpt.setIptal_date(LocalDateTime.now());

        // 3. Ataması varsa temizle (yeniden atanmasın)
        rpt.setPersonalId(null);
        rpt.setAtanma_date(null);

        // 4. Kaydet ve döndür
        return bugReportRepository.save(rpt);
    }

    @Override
    public Optional<Report> findById(Integer id) {
        return bugReportRepository.findById(id);
    }

    @Override
    @Transactional
    public Report completeReport(Integer reportId, Integer personalId) {
        Report rpt = bugReportRepository.findById(reportId)
                .orElseThrow(() -> new EntityNotFoundException("Report not found: " + reportId));

        // 1. Durumu ve tarih
        rpt.setStatus(TaskStatus.TAMAMLANDI);
        rpt.setBitis_date(LocalDateTime.now());

        // 2. Son işlem yapanın ID ve adı
        rpt.setPersonalId(personalId);
        User actor = userDashBoardRepository.findById(personalId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + personalId));
        rpt.setPersonal_name(actor.getName() + " " + actor.getSurName());

        return bugReportRepository.save(rpt);
    }

}
