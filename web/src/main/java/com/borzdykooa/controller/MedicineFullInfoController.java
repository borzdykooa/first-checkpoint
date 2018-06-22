package com.borzdykooa.controller;

import com.borzdykooa.entity.Medicine;
import com.borzdykooa.entity.Prescription;
import com.borzdykooa.entity.Review;
import com.borzdykooa.service.MedicineService;
import com.borzdykooa.service.PrescriptionService;
import com.borzdykooa.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MedicineFullInfoController extends BaseController {

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/medicineFullInfo")
    public String showFullInfo(Model model, Long id) {
        Medicine medicine = medicineService.find(id);
        List<Review> reviews = reviewService.findAllByMedicineId(id);
        model.addAttribute("medicine", medicine);
        model.addAttribute("reviews", reviews);
        String login = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Prescription> prescriptions = prescriptionService.findAllByLoginAndMedicineId(login, id, LocalDate.now(), 0L);
        if (!prescriptions.isEmpty()) {
            model.addAttribute("prescription", prescriptions.get(0));
        }
        model.addAttribute("prescriptions", prescriptions);
        return "medicine-full-info";
    }
}
