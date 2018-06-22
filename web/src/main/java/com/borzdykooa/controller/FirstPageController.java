package com.borzdykooa.controller;

import com.borzdykooa.dto.PaginationDto;
import com.borzdykooa.entity.Medicine;
import com.borzdykooa.entity.PharmacyGroup;
import com.borzdykooa.service.MedicineService;
import com.borzdykooa.service.PharmacyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@SessionAttributes({"paginationDto", "medicines"})
@Controller
public class FirstPageController extends BaseController {

    private static final int LIMIT = 5;
    private static final int PAGE = 1;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private PharmacyGroupService pharmacyGroupService;

    @GetMapping("/firstPage")
    public String showFirstPage(Model model) {
        PaginationDto paginationDto = new PaginationDto();
        paginationDto.setPage(PAGE);
        paginationDto.setLimit(LIMIT);
        paginationDto.setNeedPrescription(false);
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        List<PharmacyGroup> groups = pharmacyGroupService.findAll();
        model.addAttribute("paginationDto", paginationDto);
        model.addAttribute("login", login);
        model.addAttribute("groups",groups);
        return "first-page";
    }

    @PostMapping("/firstPage")
    public String findComplex(Model model, PaginationDto paginationDto) {
        List<Medicine> medicines = medicineService.findComplex(paginationDto);
        model.addAttribute("medicines", medicines);
        model.addAttribute("paginationDto", paginationDto);
        return "redirect:/medicines";
    }
}
