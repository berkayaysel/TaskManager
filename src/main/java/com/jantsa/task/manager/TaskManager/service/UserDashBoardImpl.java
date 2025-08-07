package com.jantsa.task.manager.TaskManager.service;


import com.jantsa.task.manager.TaskManager.entity.User;
import com.jantsa.task.manager.TaskManager.repository.UserDashBoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDashBoardImpl implements UserDashBoard{

    @Autowired
    UserDashBoardRepository userDashBoardRepository;


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
}

