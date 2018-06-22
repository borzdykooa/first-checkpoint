package com.borzdykooa.controller;

import com.borzdykooa.entity.Client;
import com.borzdykooa.entity.Medicine;
import com.borzdykooa.entity.Prescription;
import com.borzdykooa.service.ClientService;
import com.borzdykooa.service.MedicineService;
import com.borzdykooa.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class SavePrescriptionController extends BaseController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private PrescriptionService prescriptionService;

    @ModelAttribute("clients")
    public List<Client> clients() {
        return clientService.findAll();
    }

    @ModelAttribute("medicines")
    public List<Medicine> medicines() {
        return medicineService.findAllNeedPrescription();
    }

    @GetMapping("/savePrescription")
    public String showFirstPage(Model model) {
        Prescription prescription = new Prescription();
        prescription.setQuantity(1L);
        model.addAttribute("prescription", prescription);
        return "save-prescription";
    }

    @PostMapping("/savePrescription")
    public String savePrescription(Long name, Long quantity, Long medicine, Long client, String validity) {
        Medicine med = medicineService.find(medicine);
        Client user = clientService.find(client);
        Prescription prescription = new Prescription(name, user, med, quantity, LocalDate.parse(validity));
        prescriptionService.save(prescription);
        return "redirect:/success";
    }
}
