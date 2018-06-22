package com.borzdykooa.controller;

import com.borzdykooa.entity.PharmacyGroup;
import com.borzdykooa.service.PharmacyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GroupsController extends BaseController {

    @Autowired
    private PharmacyGroupService pharmacyGroupService;

    @GetMapping("/groups")
    public String showAllGroups(Model model, String name) {
        List<PharmacyGroup> groups = pharmacyGroupService.findByName(name);
        model.addAttribute("groups", groups);
        return "groups";
    }
}
