package com.borzdykooa.controller;

import com.borzdykooa.entity.Prescription;
import com.borzdykooa.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserPrescriptionsController extends BaseController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/userPrescriptions")
    public String userPrescriptions(Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Prescription> userPrescriptions = prescriptionService.findAllByLogin(login);
        model.addAttribute("userPrescriptions", userPrescriptions);
        return "user-prescriptions";
    }
}
