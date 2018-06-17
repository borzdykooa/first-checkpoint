package com.borzdykooa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController extends BaseController {

    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }
}
