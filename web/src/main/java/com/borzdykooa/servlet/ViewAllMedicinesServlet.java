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

@WebServlet("/medicines")
public class ViewAllMedicinesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Medicine> medicines = UtilClass.getBean(MedicineService.class).findAll();
        req.setAttribute("medicines", medicines);
        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/medicines-list.jsp")
                .forward(req, resp);
    }
}
