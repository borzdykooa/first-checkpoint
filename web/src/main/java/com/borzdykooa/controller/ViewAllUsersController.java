package com.borzdykooa.controller;

import com.borzdykooa.entity.Client;
import com.borzdykooa.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewAllUsersController extends BaseController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        List<Client> clients = clientService.findAll();
        model.addAttribute("clients", clients);
        return "users";
    }
}
