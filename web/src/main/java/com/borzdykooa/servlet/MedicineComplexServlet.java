package com.borzdykooa.servlet;

import com.borzdykooa.entity.Medicine;
import com.borzdykooa.service.MedicineService;
import com.borzdykooa.util.UtilClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/medicineComplex")
public class MedicineComplexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String limit = req.getParameter("limit");
        String page = req.getParameter("page");
        String partName = req.getParameter("partName");
        String partDescription = req.getParameter("partDescription");
        String needPrescription = req.getParameter("needPrescription");

        List<Medicine> medicines = UtilClass.getBean(MedicineService.class).findComplex(Integer.valueOf(limit), Integer.valueOf(page), partName, partDescription, Boolean.valueOf(needPrescription));
        req.setAttribute("medicines", medicines);
        req.getSession().setAttribute("limit", limit);
        req.getSession().setAttribute("partName", partName);
        req.getSession().setAttribute("partDescription", partDescription);
        req.getSession().setAttribute("needPrescription", needPrescription);
        req.getSession().setAttribute("page", page);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/medicines-list.jsp")
                .forward(req, resp);
    }
}
