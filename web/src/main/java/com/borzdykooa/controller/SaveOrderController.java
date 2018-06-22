package com.borzdykooa.controller;

import com.borzdykooa.dto.BasketDto;
import com.borzdykooa.entity.Client;
import com.borzdykooa.entity.Medicine;
import com.borzdykooa.entity.Ordering;
import com.borzdykooa.entity.OrderingMedicine;
import com.borzdykooa.entity.Prescription;
import com.borzdykooa.entity.SaleInfo;
import com.borzdykooa.entity.enums.Status;
import com.borzdykooa.service.ClientService;
import com.borzdykooa.service.MedicineService;
import com.borzdykooa.service.OrderingMedicineService;
import com.borzdykooa.service.OrderingService;
import com.borzdykooa.service.PrescriptionService;
import com.borzdykooa.service.SaleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@SessionAttributes({"addToBasket", "totalSum"})
@Controller
public class SaveOrderController extends BaseController {

    @Autowired
    private OrderingService orderingService;

    @Autowired
    private OrderingMedicineService orderingMedicineService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private SaleInfoService saleInfoService;

    @GetMapping("/saveOrder")
    public String showOrder(Model model, HttpServletRequest req) {
        List<BasketDto> basket = (List<BasketDto>) req.getSession().getAttribute("addToBasket");
        double helpTotalSum = 0;
        for (int i = 0; i < basket.size(); i++) {
            helpTotalSum += Double.valueOf(basket.get(i).getOrderQuantity()) * Double.valueOf(basket.get(i).getMedicinePrice());
            BigDecimal totalSum = (BigDecimal.valueOf(helpTotalSum)).setScale(2, BigDecimal.ROUND_HALF_UP);
            req.getSession().setAttribute("totalSum", totalSum);
            model.addAttribute("totalSum", totalSum);
        }
        model.addAttribute("addToBasket", basket);
        return "save-order";
    }

    @PostMapping("/saveOrder")
    protected String saveOrder(HttpServletRequest req, Model model) throws ServletException, IOException {
        List<BasketDto> basket = (List<BasketDto>) req.getSession().getAttribute("addToBasket");
        double helpTotalSum = 0;
        BigDecimal totalSum = null;
        for (int i = 0; i < basket.size(); i++) {
            helpTotalSum += Double.valueOf(basket.get(i).getOrderQuantity()) * Double.valueOf(basket.get(i).getMedicinePrice());
            totalSum = (BigDecimal.valueOf(helpTotalSum)).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = clientService.findByLogin(login);
        Locale.setDefault(new Locale("ru", "BY"));
        Ordering ordering = new Ordering(LocalDate.now(), null, Status.PROCESSED, totalSum, client);
        orderingService.save(ordering);

        for (int i = 0; i < basket.size(); i++) {
            String medicineId = basket.get(i).getMedicineId();
            Medicine medicine = medicineService.find(Long.valueOf(medicineId));
            SaleInfo saleInfo = saleInfoService.find(medicine.getSaleInfo().getId());
            Long baseQuantity = medicine.getSaleInfo().getQuantity();
            String orderQuantity = basket.get(i).getOrderQuantity();
            OrderingMedicine orderingMedicine = new OrderingMedicine(ordering, medicine, Long.valueOf(orderQuantity));
            orderingMedicineService.save(orderingMedicine);
            saleInfo.setQuantity(baseQuantity - Long.valueOf(orderQuantity));
            saleInfoService.update(saleInfo);
            List<Prescription> prescriptions = prescriptionService.findAllByLoginAndMedicineId(login, Long.valueOf(medicineId), LocalDate.now(), 0L);
            if (!prescriptions.isEmpty()) {
                Long quantity = prescriptions.get(0).getQuantity();
                prescriptions.get(0).setQuantity(quantity - Long.valueOf(orderQuantity));
                prescriptionService.update(prescriptions.get(0));
            }
        }
        req.getSession().removeAttribute("addToBasket");
        model.addAttribute("addToBasket", null);
        model.addAttribute("totalSum", null);
        return "success";
    }
}
