package com.borzdykooa.util;

import com.borzdykooa.entity.Admin;
import com.borzdykooa.entity.Client;
import com.borzdykooa.entity.Medicine;
import com.borzdykooa.entity.Ordering;
import com.borzdykooa.entity.OrderingMedicine;
import com.borzdykooa.entity.PharmacyGroup;
import com.borzdykooa.entity.Prescription;
import com.borzdykooa.entity.Review;
import com.borzdykooa.entity.SaleInfo;
import com.borzdykooa.entity.enums.AdminRole;
import com.borzdykooa.entity.enums.Status;
import com.borzdykooa.entity.enums.UserRole;
import com.borzdykooa.entity.helpers.FullName;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class TestServiceDataImporter {

    private final SessionFactory sessionFactory;

    @Autowired
    public TestServiceDataImporter(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void importTestData() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Admin admin = new Admin("admin", "admin", UserRole.ADMIN, AdminRole.SUPER_ADMIN);
            Admin manager = new Admin("manager", "manager", UserRole.ADMIN, AdminRole.PRESCRIPTION_MANAGER);
            session.save(admin);
            session.save(manager);

            Client client = new Client("ivan", "pass", UserRole.CLIENT, new FullName("Ivanov", "Ivan", "Ivanovich"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
            Ordering firstOrder = new Ordering(LocalDate.of(2017, 3, 5), LocalDate.of(2017, 3, 5), Status.DONE, BigDecimal.valueOf(30.00), client);
            PharmacyGroup antidepressants = new PharmacyGroup("антидепрессанты");
            SaleInfo saleInfoKotoferon = new SaleInfo(BigDecimal.valueOf(5.55), 11L, true);
            Medicine kotoferon = new Medicine("котоферон", "суперлекарство", antidepressants, saleInfoKotoferon);
            OrderingMedicine orderingMedicine = new OrderingMedicine(firstOrder, kotoferon, 15L);
            Prescription prescription = new Prescription(7777725838L, client, kotoferon, 15L, LocalDate.of(2018, 5, 31));
            Review review = new Review(5, "good comment", LocalDateTime.now(), client, kotoferon);
            session.save(client);
            session.save(firstOrder);
            session.save(antidepressants);
            session.save(saleInfoKotoferon);
            session.save(kotoferon);
            session.save(orderingMedicine);
            session.save(prescription);
            session.save(review);

            Client secondClient = new Client("ivan4", "pass4", UserRole.CLIENT, new FullName("Ivanov", "Ivan", "Ivanovich"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
            Ordering secondOrder = new Ordering(LocalDate.of(2017, 3, 25), LocalDate.of(2017, 3, 5), Status.DONE, BigDecimal.valueOf(36.00), secondClient);
            PharmacyGroup antihistamines = new PharmacyGroup("антигистаминные препараты");
            SaleInfo saleInfoProstoferon = new SaleInfo(BigDecimal.valueOf(1.11), 10L, true);
            Medicine prostoferon = new Medicine("простоферон", "просто лекарство", antihistamines, saleInfoProstoferon);
            OrderingMedicine secondOrderingMedicine = new OrderingMedicine(secondOrder, prostoferon, 1L);
            Prescription secondPrescription = new Prescription(12583L, secondClient, prostoferon, 1L, LocalDate.of(2018, 5, 31));
            Review secondReview = new Review(2, "bad comment", LocalDateTime.now(), secondClient, prostoferon);
            session.save(secondClient);
            session.save(secondOrder);
            session.save(antihistamines);
            session.save(saleInfoProstoferon);
            session.save(prostoferon);
            session.save(secondOrderingMedicine);
            session.save(secondPrescription);
            session.save(secondReview);

            session.getTransaction().commit();
        }
    }

    public void deleteTestData() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from OrderingMedicine ").executeUpdate();
            session.createQuery("delete from Prescription ").executeUpdate();
            session.createQuery("delete from Review ").executeUpdate();
            session.createQuery("delete from Ordering ").executeUpdate();
            session.createQuery("delete from Client ").executeUpdate();
            session.createQuery("delete from Medicine ").executeUpdate();
            session.createQuery("delete from Admin ").executeUpdate();
            session.createQuery("delete from User ").executeUpdate();
            session.createQuery("delete from PharmacyGroup ").executeUpdate();
            session.createQuery("delete from SaleInfo ").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
