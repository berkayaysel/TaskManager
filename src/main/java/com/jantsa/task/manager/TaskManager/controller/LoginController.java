package com.jantsa.task.manager.TaskManager.controller;

import com.jantsa.task.manager.TaskManager.entity.RequestLogin;
import com.jantsa.task.manager.TaskManager.entity.User;
import com.jantsa.task.manager.TaskManager.enums.UserRole;
import com.jantsa.task.manager.TaskManager.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/task")
public class LoginController {

    @Autowired
    LoginServiceImpl loginService;

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody RequestLogin requestLogin) {
        RequestLogin loginResponse = loginService.login(requestLogin);
        RequestLogin user = loginService.login(requestLogin);
        if(loginResponse == null) {
            return ResponseEntity.status(401).body("Giriş başarısız: Kullanıcı bulunamadı veya şifre yanlış.");
        }

        UserRole userRole = loginResponse.getUserRole();
        String redirectUrl;
        if("Admin".equalsIgnoreCase(String.valueOf(userRole))) {
            redirectUrl = "/admin-dashboard"; // API endpoint'lerini kullanıyoruz
        } else if("Personal".equalsIgnoreCase(String.valueOf(userRole))) {
            redirectUrl = "/personal-dashboard";
        } else if ("User".equalsIgnoreCase(String.valueOf(userRole))) {
            redirectUrl = "/user-dashboard";
        } else {
            return ResponseEntity.status(500).body("Sunucu hatası: Tanımsız kullanıcı rolü.");
        }


        Map<String, Object> response = new HashMap<>();
        response.put("redirectUrl", redirectUrl);
        response.put("companyId", user.getCompany_id());

        return ResponseEntity.ok(response);
    }



}