package com.borzdykooa.controller;

import com.borzdykooa.entity.Prescription;
import com.borzdykooa.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewAllPrescriptionsController extends BaseController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/prescriptions")
    public String prescriptions(Model model){
        List<Prescription> prescriptions = prescriptionService.findAll();
        model.addAttribute("prescriptions",prescriptions);
        return "prescriptions";
    }

}
