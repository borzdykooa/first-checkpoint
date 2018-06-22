package com.borzdykooa.controller;

import com.borzdykooa.entity.Medicine;
import com.borzdykooa.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MedicinesInGroupController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/medicinesInGroup")
    private String medicinesInGroup(Model model, Long id){
        List<Medicine> medicinesInGroup = medicineService.findByGroupId(id);
        model.addAttribute("medicinesInGroup",medicinesInGroup);
        return "medicines-in-group";
    }
}
