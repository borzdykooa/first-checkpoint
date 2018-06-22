package com.borzdykooa.controller;

import com.borzdykooa.entity.Admin;
import com.borzdykooa.entity.enums.AdminRole;
import com.borzdykooa.entity.enums.UserRole;
import com.borzdykooa.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SavePrescriptionManagerController extends BaseController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/saveManager")
    public String showLoginPage() {
        return "save-manager";
    }

    @PostMapping("/saveManager")
    public String saveUser(String username, String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pass = bCryptPasswordEncoder.encode(password);
        Admin manager = new Admin(username, pass, UserRole.ADMIN, AdminRole.PRESCRIPTION_MANAGER);
        adminService.save(manager);
        return "success";
    }
}
