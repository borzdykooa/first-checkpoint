package com.borzdykooa.controller;

import com.borzdykooa.dto.PaginationDto;
import com.borzdykooa.entity.Medicine;
import com.borzdykooa.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@SessionAttributes("medicines")
@Controller
public class FirstPageController extends BaseController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/firstPage")
    public String showFirstPage(Model model) {
        PaginationDto paginationDto = new PaginationDto();
        paginationDto.setPage(1);
        paginationDto.setLimit(10);
        paginationDto.setNeedPrescription(false);
        model.addAttribute("paginationDto", paginationDto);
        return "first-page";
    }

    @PostMapping("/firstPage")
    public String findComplex(Model model, PaginationDto paginationDto) {
        List<Medicine> medicines = medicineService.findComplex(paginationDto);
        model.addAttribute("medicines", medicines);
        model.addAttribute("paginationDto",paginationDto);
        return "medicines";
    }
}
