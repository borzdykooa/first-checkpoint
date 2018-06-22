package com.borzdykooa.controller;

import com.borzdykooa.dto.BasketDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SessionAttributes("addToBasket")
@Controller
public class AddToBasketController extends BaseController {

    @GetMapping("/addToBasket")
    protected String doGet(Model model, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String medicineId = req.getParameter("medicineId");
        String medicineName = req.getParameter("medicineName");
        String medicinePrice = req.getParameter("medicinePrice");
        String medicineQuantity = req.getParameter("medicineQuantity");
        String orderQuantity = req.getParameter("orderQuantity");
        String newQuantity = String.valueOf(Integer.valueOf(medicineQuantity) - Integer.valueOf(orderQuantity));
        BigDecimal helpSum = (BigDecimal.valueOf(Double.valueOf(medicinePrice)
                * Double.valueOf(orderQuantity))).setScale(2, BigDecimal.ROUND_HALF_UP);
        String sum = String.valueOf(helpSum);

        List<BasketDto> basket = (List<BasketDto>) req.getSession().getAttribute("addToBasket");
        if (basket == null) {
            basket = new ArrayList<>();
            req.getSession().setAttribute("addToBasket", basket);
            model.addAttribute("addToBasket", basket);
        }
        basket.add(new BasketDto(medicineId, medicineName, medicinePrice, medicineQuantity, orderQuantity, newQuantity, sum));
        return "/success";
    }
}
