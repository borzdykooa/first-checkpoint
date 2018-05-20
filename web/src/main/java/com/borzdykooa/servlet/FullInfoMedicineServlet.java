package com.borzdykooa.servlet;

import com.borzdykooa.entity.Medicine;
import com.borzdykooa.service.MedicineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/medicineFullInfo")
public class FullInfoMedicineServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            Medicine fullMedicineInfo = MedicineService.getInstance().find(Long.valueOf(id));
            req.setAttribute("medicine", fullMedicineInfo);
            getServletContext()
                    .getRequestDispatcher("/WEB-INF/jsp/medicine-full-info.jsp")
                    .forward(req, resp);
        }
    }
}
