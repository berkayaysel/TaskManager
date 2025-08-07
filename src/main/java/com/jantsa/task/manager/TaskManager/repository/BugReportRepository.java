package com.jantsa.task.manager.TaskManager.repository;

import com.jantsa.task.manager.TaskManager.dto.BugReportRequestDto;
import com.jantsa.task.manager.TaskManager.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BugReportRepository extends JpaRepository<Report,Integer> {







}
