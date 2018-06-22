package com.borzdykooa.controller;

import com.borzdykooa.entity.Medicine;
import com.borzdykooa.entity.SaleInfo;
import com.borzdykooa.service.MedicineService;
import com.borzdykooa.service.SaleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class UpdateMedicineController extends BaseController {

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private SaleInfoService saleInfoService;

    @ModelAttribute("medicines")
    public List<Medicine> pharmacyGroups() {
        return medicineService.findAll();
    }

    @GetMapping("/updateMedicine")
    public String openMedicinePage(Model model) {
        model.addAttribute("medicine", new Medicine());
        return "update-medicine";
    }

    @PostMapping("/updateMedicine")
    public String updateMedicine(String newName, String newDescription, Long medicine, Long saleInfoId, BigDecimal newPrice, Long newQuantity, Boolean newNeedPrescription) {
        Medicine updatedMedicine = medicineService.find(medicine);
        SaleInfo saleInfo = saleInfoService.find(updatedMedicine.getSaleInfo().getId());
        if (newQuantity != null) {
            saleInfo.setQuantity(newQuantity);
        }
        if (newPrice != null) {
            saleInfo.setPrice(newPrice);
        }
        if (newNeedPrescription != null) {
            saleInfo.setNeedPrescription(newNeedPrescription);
        }
        if (newName != "") {
            updatedMedicine.setName(newName);
        }
        if (newDescription != "") {
            updatedMedicine.setDescription(newDescription);
        }
        saleInfoService.update(saleInfo);
        medicineService.update(updatedMedicine);
        return "redirect:/success";
    }
}
