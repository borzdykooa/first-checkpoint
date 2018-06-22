package com.borzdykooa.controller;

import com.borzdykooa.entity.OrderingMedicine;
import com.borzdykooa.service.OrderingMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserOrdersController extends BaseController {

    @Autowired
    private OrderingMedicineService orderingMedicineService;

    @GetMapping("/userOrders")
    public String userOrders(Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        List<OrderingMedicine> orderingMedicines = orderingMedicineService.findOrderingByUserLogin(login);
        model.addAttribute("orderingMedicines", orderingMedicines);
        return "user-orders";
    }
}
