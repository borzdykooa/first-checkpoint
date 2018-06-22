package com.borzdykooa.controller;

import com.borzdykooa.entity.Ordering;
import com.borzdykooa.entity.OrderingMedicine;
import com.borzdykooa.entity.enums.Status;
import com.borzdykooa.service.OrderingMedicineService;
import com.borzdykooa.service.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class UpdateOrderStatusDateController extends BaseController {

    @Autowired
    private OrderingMedicineService orderingMedicineService;

    @Autowired
    private OrderingService orderingService;

    @GetMapping("/updateOrders")
    public String openGroupPage(Model model) {
        List<OrderingMedicine> orderingMedicines=orderingMedicineService.findProcessedOrdering(Status.PROCESSED);
        model.addAttribute("processedOrders",orderingMedicines);
        return "update-orders";
    }

    @PostMapping("/updateOrders")
    public String updateOrders(Long orderingMedicine) {
        OrderingMedicine updatedOrderingMedicine = orderingMedicineService.find(orderingMedicine);
        Ordering ordering = updatedOrderingMedicine.getOrdering();
        ordering.setOrderingClothingDate(LocalDate.now());
        ordering.setStatus(Status.DONE);
        orderingService.update(ordering);
        return "redirect:/success";
    }
}
