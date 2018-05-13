package by.borzdykooa.servlet;

import by.borzdykooa.entity.Medicine;
import by.borzdykooa.service.MedicineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/medicine")
public class MedicineServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Medicine medicine = MedicineService.getInstance().getMedicineById();
        req.setAttribute("medicine", medicine);
        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/medicine.jsp")
                .forward(req, resp);
    }
}
