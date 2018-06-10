package com.borzdykooa.controller;

import com.borzdykooa.entity.Medicine;
import com.borzdykooa.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MedicineComplexController {

    private MedicineService medicineService;

    @Autowired
    public MedicineComplexController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("/medicineComplex")
    public String openMedicinesList(Model model, Integer limit, Integer page, String partName, String partDescription, Boolean needPrescription) {
        List<Medicine> medicines = medicineService.findComplex(limit, page, partName, partDescription, needPrescription);
        model.addAttribute(medicines);

        return "medicines-list";
    }
}
