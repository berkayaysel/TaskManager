package com.jantsa.task.manager.TaskManager.service;

import com.jantsa.task.manager.TaskManager.entity.Report;
import com.jantsa.task.manager.TaskManager.entity.User;

import java.util.List;

public interface AdminDashBoard {

    public List<User> getAllPersonal();
    public List <Report> findAll();

}
