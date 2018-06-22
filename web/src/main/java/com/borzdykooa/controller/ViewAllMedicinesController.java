package com.borzdykooa.controller;

import com.borzdykooa.entity.Medicine;
import com.borzdykooa.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewAllMedicinesController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/medicinesList")
    public String showAllMedicines(Model model) {
        List<Medicine> medicines = medicineService.findAll();
        model.addAttribute("medicines", medicines);
        return "medicines-list";
    }
}
