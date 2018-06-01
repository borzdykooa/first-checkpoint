package com.borzdykooa.servlet;

import com.borzdykooa.entity.Medicine;
import com.borzdykooa.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/medicines")
public class ViewAllMedicinesServlet extends HttpServlet {

    @Autowired
    private MedicineService medicineService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Medicine> medicines = medicineService.findAll();
        req.setAttribute("medicines", medicines);
        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/medicines-list.jsp")
                .forward(req, resp);
    }
}
