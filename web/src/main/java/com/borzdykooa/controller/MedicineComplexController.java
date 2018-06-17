//package com.borzdykooa.controller;
//
//import com.borzdykooa.entity.Medicine;
//import com.borzdykooa.service.MedicineService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//
//@Controller
//public class MedicineComplexController extends BaseController {
//
////    private MedicineService medicineService;
////
////    @Autowired
////    public MedicineComplexController(MedicineService medicineService) {
////        this.medicineService = medicineService;
////    }
//
//
////    @GetMapping("/medicineComplex")
////    public String findComplex(Model model, List<Medicine> medicines) {
////        model.addAttribute("medicines", medicines);
////        return "medicines";
////    }
//    @Autowired
//    private MedicineService medicineService;
//
//    @GetMapping("/medicineComplex")
//    public String showAllMedicines(Model model, List<Medicine>medicines) {
////        List<Medicine> medicines = medicineService.findComplex(paginationDto);
//        model.addAttribute("medicines", medicines);
//        return "medicines";
//    }
//}
