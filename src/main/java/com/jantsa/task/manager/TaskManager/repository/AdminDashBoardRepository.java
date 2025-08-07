package com.jantsa.task.manager.TaskManager.repository;

import com.jantsa.task.manager.TaskManager.entity.Report;
import com.jantsa.task.manager.TaskManager.entity.User;
import com.jantsa.task.manager.TaskManager.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDashBoardRepository extends JpaRepository<User,Integer> {

    List <User> findByUserRole(UserRole userRole);



}
