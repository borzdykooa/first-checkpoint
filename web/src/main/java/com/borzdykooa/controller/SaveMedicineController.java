package com.borzdykooa.controller;

import com.borzdykooa.entity.Medicine;
import com.borzdykooa.entity.PharmacyGroup;
import com.borzdykooa.entity.SaleInfo;
import com.borzdykooa.service.MedicineService;
import com.borzdykooa.service.PharmacyGroupService;
import com.borzdykooa.service.SaleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SaveMedicineController {

    @Autowired
    private PharmacyGroupService pharmacyGroupService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private SaleInfoService saleInfoService;

    @ModelAttribute("pharmacyGroups")
    public List<PharmacyGroup> pharmacyGroups() {
        return pharmacyGroupService.findAll();
    }

    @GetMapping("/saveMedicine")
    public String openMedicinePage(Model model) {
        model.addAttribute("medicine", new Medicine());
        return "save-medicine";
    }

    @PostMapping("/saveMedicine")
    public String saveMedicine(SaleInfo saleInfo,String name, String description, Long pharmacyGroup) {
        saleInfoService.save(saleInfo);
        PharmacyGroup pg = pharmacyGroupService.find(pharmacyGroup);
        Medicine medicine = new Medicine(name, description, pg, saleInfo);
        medicineService.save(medicine);
        return "redirect:/success";
    }
}
