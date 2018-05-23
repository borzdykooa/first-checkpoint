package com.borzdykooa.dao;

import com.borzdykooa.entity.Client;
import com.borzdykooa.entity.Medicine;
import com.borzdykooa.entity.Ordering;
import com.borzdykooa.entity.OrderingMedicine;
import com.borzdykooa.entity.PharmacyGroup;
import com.borzdykooa.entity.SaleInfo;
import com.borzdykooa.entity.enums.Status;
import com.borzdykooa.entity.enums.UserRole;
import com.borzdykooa.entity.helpers.FullName;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class TestDaoDataImporter {

    private static TestDaoDataImporter INSTANCE = new TestDaoDataImporter();

    public static TestDaoDataImporter getInstance() {
        return INSTANCE;
    }

    public void importTestData(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            PharmacyGroup antidepressants = new PharmacyGroup("антидепрессанты");
            SaleInfo saleInfoKotoferon = new SaleInfo(BigDecimal.valueOf(5.55), 11L, true);
            Medicine kotoferon = new Medicine("котоферон", "суперлекарство", antidepressants, saleInfoKotoferon);
            session.save(antidepressants);
            session.save(saleInfoKotoferon);
            session.save(kotoferon);

            PharmacyGroup antihistamines = new PharmacyGroup("антигистаминные препараты");
            SaleInfo saleInfoprostoferon = new SaleInfo(BigDecimal.valueOf(5.55), 11L, true);
            Medicine prostoferon = new Medicine("простоферон", "просто лекарство", antihistamines, saleInfoprostoferon);
            session.save(antihistamines);
            session.save(saleInfoprostoferon);
            session.save(prostoferon);

            PharmacyGroup newGroup = new PharmacyGroup("новая группа");
            SaleInfo saleInfoNovoferon = new SaleInfo(BigDecimal.valueOf(5.55), 11L, true);
            session.save(saleInfoNovoferon);
            session.save(newGroup);

            Client client = new Client("ivan", "pass", UserRole.CLIENT, new FullName("Ivanov", "Ivan", "Ivanovich"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
            Ordering firstOrder = new Ordering(LocalDate.of(2017, 3, 5), LocalDate.of(2017, 3, 5), Status.DONE, BigDecimal.valueOf(36), client);
            OrderingMedicine orderingMedicine = new OrderingMedicine(firstOrder, kotoferon, 15L);
            session.save(client);
            session.save(firstOrder);
            session.save(orderingMedicine);

            Ordering secondOrder = new Ordering(LocalDate.of(2018, 3, 5), LocalDate.of(2018, 3, 5), Status.DONE, BigDecimal.valueOf(36), client);
            OrderingMedicine orderingMedicine2 = new OrderingMedicine(firstOrder, kotoferon, 15L);
            session.save(secondOrder);
            session.save(orderingMedicine2);

            session.getTransaction().commit();
        }
    }
}
