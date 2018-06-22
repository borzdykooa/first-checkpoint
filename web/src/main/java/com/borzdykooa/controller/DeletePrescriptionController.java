package com.borzdykooa.controller;

import com.borzdykooa.entity.Prescription;
import com.borzdykooa.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DeletePrescriptionController extends BaseController {

    @Autowired
    private PrescriptionService prescriptionService;

    @ModelAttribute("prescriptions")
    public List<Prescription> prescriptions() {
        return prescriptionService.findAll();
    }

    @GetMapping("/deletePrescription")
    public String openPrescriptionPage(Model model) {
        model.addAttribute("prescription", new Prescription());
        return "delete-prescription";
    }

    @PostMapping("/deletePrescription")
    public String deletePrescription(Long prescription) {
        Prescription deletedPrescription = prescriptionService.find(prescription);
        prescriptionService.delete(deletedPrescription);
        return "redirect:/success";
    }
}
