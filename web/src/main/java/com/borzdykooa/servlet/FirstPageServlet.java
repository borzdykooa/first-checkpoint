package com.borzdykooa.servlet;

import com.borzdykooa.service.PharmacyGroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/firstPage")
public class FirstPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("groups", PharmacyGroupService.getInstance().getAllGroups());
        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/first-page.jsp")
                .forward(req, resp);
    }
}
