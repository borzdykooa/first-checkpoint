package com.borzdykooa.controller;

import com.borzdykooa.entity.Client;
import com.borzdykooa.entity.enums.UserRole;
import com.borzdykooa.entity.helpers.FullName;
import com.borzdykooa.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class SaveUserController extends BaseController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/saveUser")
    public String showLoginPage() {
        return "save-user";
    }

    @PostMapping("/saveUser")
    public String saveUser(String username, String password, String lastName, String firstName, String patronymic, String dateOfBirth, String telephoneNumber, String address) {
        FullName fullName = new FullName(lastName, firstName, patronymic);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pass = bCryptPasswordEncoder.encode(password);
        Client client = new Client(username, pass, UserRole.CLIENT, fullName, LocalDate.parse(dateOfBirth), telephoneNumber, address);
        clientService.save(client);
        return "login";
    }
}