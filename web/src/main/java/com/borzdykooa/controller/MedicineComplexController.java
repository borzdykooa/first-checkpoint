package com.borzdykooa.controller;

import com.borzdykooa.dto.PaginationDto;
import com.borzdykooa.entity.Medicine;
import com.borzdykooa.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@SessionAttributes({"paginationDto", "medicines"})
@Controller
public class MedicineComplexController extends BaseController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/medicines")
    public String showAllMedicines(Model model, PaginationDto paginationDto) {
        List<Medicine> medicines = medicineService.findComplex(paginationDto);
        model.addAttribute("medicines", medicines);
        return "medicines";
    }
}
