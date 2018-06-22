package com.borzdykooa.controller;

import com.borzdykooa.dto.BasketDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@SessionAttributes({"addToBasket", "totalSum"})
@Controller
public class BasketController extends BaseController {

    @GetMapping("/basket")
    public String showBasket(Model model, HttpServletRequest req) {
        List<BasketDto> basket = (List<BasketDto>) req.getSession().getAttribute("addToBasket");
        if (basket != null) {
            double helpTotalSum = 0;
            for (int i = 0; i < basket.size(); i++) {
                helpTotalSum += Double.valueOf(basket.get(i).getOrderQuantity()) * Double.valueOf(basket.get(i).getMedicinePrice());
                BigDecimal totalSum = (BigDecimal.valueOf(helpTotalSum)).setScale(2, BigDecimal.ROUND_HALF_UP);
                req.getSession().setAttribute("totalSum", totalSum);
                model.addAttribute("totalSum", totalSum);
            }
        }
        model.addAttribute("addToBasket", basket);
        return "basket";
    }

    @PostMapping("/basket")
    protected String cleanBasket(Model model, HttpServletRequest req) {
        req.getSession().removeAttribute("addToBasket");
        req.getSession().removeAttribute("totalSum");
        model.addAttribute("totalSum", null);
        model.addAttribute("addToBasket", null);
        return "redirect:/success";
    }
}
