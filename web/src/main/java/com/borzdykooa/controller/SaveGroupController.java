package com.borzdykooa.controller;

import com.borzdykooa.entity.PharmacyGroup;
import com.borzdykooa.service.PharmacyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SaveGroupController {

    @Autowired
    private PharmacyGroupService pharmacyGroupService;

    @GetMapping("/saveGroup")
    public String openGroupPage(Model model) {
        model.addAttribute("group", new PharmacyGroup());
        return "save-group";
    }

    @PostMapping("/saveGroup")
    public String saveGroup(PharmacyGroup pharmacyGroup) {
        pharmacyGroupService.save(pharmacyGroup);
        return "redirect:/success";
    }
}
