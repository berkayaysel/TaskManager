package com.jantsa.task.manager.TaskManager.controller;

import com.jantsa.task.manager.TaskManager.entity.RequestLogin;
import com.jantsa.task.manager.TaskManager.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/task")
public class LoginController {

    @Autowired
    LoginServiceImpl loginService;

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody RequestLogin requestLogin) {
        RequestLogin loginResponse = loginService.login(requestLogin);
        System.out.println("giriş bilgileri geldi");
        if(loginResponse == null) {
            return ResponseEntity.status(401).body("Giriş başarısız: Kullanıcı bulunamadı veya şifre yanlış.");
        }

        String userRole = loginResponse.getRole();
        String redirectUrl;
        if("Admin".equalsIgnoreCase(userRole)) {
            redirectUrl = "/admin-dashboard"; // API endpoint'lerini kullanıyoruz
        } else if("Personal".equalsIgnoreCase(userRole)) {
            redirectUrl = "/personal-dashboard";
        } else if ("User".equalsIgnoreCase(userRole)) {
            redirectUrl = "/user-dashboard";
        } else {
            return ResponseEntity.status(500).body("Sunucu hatası: Tanımsız kullanıcı rolü.");
        }

        // Yönlendirme URL'sini JSON formatında döndür.
        // Frontend bu yanıtı alıp kendi yönlendirme işlemini yapacak.
        return ResponseEntity.ok().body("{\"redirectUrl\": \"" + redirectUrl + "\"}");
    }
}