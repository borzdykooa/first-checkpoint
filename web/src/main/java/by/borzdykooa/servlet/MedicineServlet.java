package by.borzdykooa.servlet;

import by.borzdykooa.entity.Medicine;
import by.borzdykooa.service.MedicineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/medicines")
public class MedicineServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Medicine> medicines = MedicineService.getInstance().findAllMedicines();
        req.setAttribute("medicines", medicines);
        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/medicines.jsp")
                .forward(req, resp);
    }
}
