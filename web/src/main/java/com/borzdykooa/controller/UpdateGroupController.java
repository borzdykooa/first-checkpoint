package com.borzdykooa.controller;

import com.borzdykooa.entity.PharmacyGroup;
import com.borzdykooa.service.PharmacyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UpdateGroupController extends BaseController {

    @Autowired
    private PharmacyGroupService pharmacyGroupService;

    @ModelAttribute("pharmacyGroups")
    public List<PharmacyGroup> pharmacyGroups() {
        return pharmacyGroupService.findAll();
    }

    @GetMapping("/updateGroup")
    public String openGroupPage() {
        return "update-group";
    }

    @PostMapping("/updateGroup")
    public String updateGroup(String updatedPharmacyGroup, Long pharmacyGroup) {
        PharmacyGroup group = pharmacyGroupService.find(pharmacyGroup);
        group.setName(updatedPharmacyGroup);
        pharmacyGroupService.update(group);
        return "redirect:/success";
    }
}
