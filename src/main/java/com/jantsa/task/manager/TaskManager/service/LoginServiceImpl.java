package com.jantsa.task.manager.TaskManager.service;

import com.jantsa.task.manager.TaskManager.entity.RequestLogin;
import com.jantsa.task.manager.TaskManager.entity.User;
import com.jantsa.task.manager.TaskManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    UserRepository userRepository;

    @Override
    public RequestLogin login(RequestLogin requestLogin) {

        Integer companyIdFromRequest = requestLogin.getCompany_id();
        Optional<User> userOptional = userRepository.findByCompanyId(companyIdFromRequest);
        if (userOptional.isPresent()) {
            // Kullanıcı bulunduysa
            User user = userOptional.get();

            // 4. Request'ten gelen düz metin şifreyi, veritabanındaki şifre ile doğrudan karşılaştırıyoruz.
            // Bu yöntem, şifrelerin veritabanında da düz metin olarak saklandığı anlamına gelir.
            if (requestLogin.getPassword().equals(user.getPassword())) {
                // Şifreler eşleşiyorsa, başarılı oturum açma
                System.out.println("Oturum açma başarılı!");

                String userRole = user.getUserRole();
                requestLogin.setRole(userRole);


                System.out.println(userRole);
                if("Admin".equalsIgnoreCase(userRole)) {
                    System.out.println("Admin Paneline Yönlendiriliyor... ");
                    return requestLogin;
                } else if("Personal".equalsIgnoreCase(userRole)) {
                    System.out.println("Personel Paneline Yönlendiriliyor...");
                    return requestLogin;
                } else if("User".equalsIgnoreCase(userRole)) {
                    System.out.println("Kullanıcı Paneline Yönlendiriliyor...");
                    return requestLogin;
                } else {
                    System.out.println("Tanımsız Rol!");
                    return null;
                }
            } else {
                // Şifreler eşleşmiyorsa
                System.out.println("Şifre yanlış!");
                return null; // Veya başarısızlığı belirten bir nesne döndürün
            }

        } else {
            // Kullanıcı bulunamadıysa
            System.out.println("Kullanıcı bulunamadı!");
            return null; // Veya başarısızlığı belirten bir nesne döndürün

        }
    }
}
