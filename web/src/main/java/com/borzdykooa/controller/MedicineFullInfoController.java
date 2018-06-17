package com.borzdykooa.controller;

import com.borzdykooa.entity.Medicine;
import com.borzdykooa.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MedicineFullInfoController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("medicineFullInfo")
    public String showFullInfo(Model model,  Long id){
        Medicine medicine = medicineService.find(id);
        model.addAttribute("medicine",medicine);
        return "medicine-full-info";
    }
}
