package com.jantsa.task.manager.TaskManager.controller;

import com.jantsa.task.manager.TaskManager.entity.RequestLogin;
import com.jantsa.task.manager.TaskManager.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@RequestMapping(path = "/task")
public class LoginController {

    @Autowired
    LoginServiceImpl loginService;

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody RequestLogin requestLogin) {
        RequestLogin loginResponse = loginService.login(requestLogin);
        System.out.println("giriş bilgileri geldi");
        if(loginResponse == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Giriş başarısız: Kullanıcı bulunamadı veya şifre yanlış.");
        }

        String userRole = loginResponse.getRole();
        String redirectUrl;
        if("Admin".equalsIgnoreCase(userRole)) {
            redirectUrl = "http://localhost:8080/admin-dashboard";
        } else if("Personal".equalsIgnoreCase(userRole)) {
            redirectUrl = "http://localhost:8080/personal-dashboard";
        } else if ("User".equalsIgnoreCase(userRole)) {
            redirectUrl = "http://localhost:8080/user-dashboard";
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Sunucu hatası: Tanımsız kullanıcı rolü.");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirectUrl));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping(path = "/login")
    public String showLoginPage(){
        return "login";
    }

}
