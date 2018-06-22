package com.borzdykooa.controller;

import com.borzdykooa.entity.PharmacyGroup;
import com.borzdykooa.service.PharmacyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CacheController extends BaseController {

    @Autowired
    private PharmacyGroupService pharmacyGroupService;

    @GetMapping("/cache")
    public String openGroupPage(Model model) {
        model.addAttribute("group", new PharmacyGroup());
        return "cache";
    }

    @PostMapping("/cache")
    public String saveGroup(String name, Model model) {
        List<PharmacyGroup> groups = pharmacyGroupService.findByName(name);
        model.addAttribute("groups", groups);
        return "groups";
    }
}
