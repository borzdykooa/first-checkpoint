package com.borzdykooa.controller;

import com.borzdykooa.entity.Client;
import com.borzdykooa.entity.Medicine;
import com.borzdykooa.entity.Review;
import com.borzdykooa.service.ClientService;
import com.borzdykooa.service.MedicineService;
import com.borzdykooa.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.Locale;

@Controller
public class SaveReviewController extends BaseController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/saveReview")
    public String showReviewForm() {
        return "medicine-full-info";
    }

    @PostMapping("/saveReview")
    public String saveReview(Integer mark, String comment, Long medicine) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = clientService.findByLogin(login);
        Medicine med = medicineService.find(medicine);
        Locale.setDefault(new Locale("ru", "BY"));
        Review review = new Review(mark, comment, LocalDateTime.now(), client, med);
        reviewService.save(review);
        return "redirect:/success";
    }
}
