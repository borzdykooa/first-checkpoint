package com.borzdykooa.controller;

import com.borzdykooa.entity.Medicine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstPageController {

    @GetMapping("/firstPage")
    public String showFirstPage(Model model, Integer limit, Integer page, String partName, String partDescription, Boolean needPrescription) {
        Medicine medicine = new Medicine();
        model.addAttribute("medicine", medicine);
        model.addAttribute("limit", limit);
        model.addAttribute("page", page);
        model.addAttribute("partName", partName);
        model.addAttribute("partDescription", partDescription);
        model.addAttribute("needPrescription", needPrescription);
        return "first-page";
    }
}
