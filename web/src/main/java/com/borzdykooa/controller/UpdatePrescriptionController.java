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
public class UpdatePrescriptionController extends BaseController {

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private ClientService clientService;

    @ModelAttribute("prescriptions")
    public List<Prescription> prescriptions() {
        return prescriptionService.findAll();
    }

    @ModelAttribute("medicines")
    public List<Medicine> medicines() {
        return medicineService.findAll();
    }

    @ModelAttribute("clients")
    public List<Client> clients() {
        return clientService.findAll();
    }

    @GetMapping("/updatePrescription")
    public String openPrescriptionPage(Model model) {
        model.addAttribute("prescription", new Prescription());
        return "update-prescription";
    }

    @PostMapping("/updatePrescription")
    public String updatePrescription(Long prescription, Long newName, Long medicine, Long client, Long newQuantity, String newValidity) {
        Prescription updatedPrescription = prescriptionService.find(prescription);
        Client cl = clientService.find(client);
        Medicine med = medicineService.find(medicine);
        if (newQuantity != null) {
            updatedPrescription.setQuantity(newQuantity);
        }
        if (newName != null) {
            updatedPrescription.setName(newName);
        }
        if (newValidity != "") {
            updatedPrescription.setValidity(LocalDate.parse(newValidity));
        }
        if (med != null) {
            updatedPrescription.setPrescriptionMedicine(med);
        }
        if (cl != null) {
            updatedPrescription.setPrescriptionUser(cl);
        }
        prescriptionService.update(updatedPrescription);
        return "redirect:/success";
    }
}
