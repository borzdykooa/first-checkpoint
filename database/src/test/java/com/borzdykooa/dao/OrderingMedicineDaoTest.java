package com.borzdykooa.dao;

import com.borzdykooa.dao.implementations.OrderingMedicineDao;
import com.borzdykooa.dao.interfaces.OrderingMedicineDaoIF;
import com.borzdykooa.entity.OrderingMedicine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class OrderingMedicineDaoTest {

    private OrderingMedicineDaoIF orderingMedicineDao = OrderingMedicineDao.getInstance();
    private SessionFactory sessionFactory;

    @Before
    public void before() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        TestDaoDataImporter.getInstance().importTestData(sessionFactory);
    }

    @After
    public void after() {
        sessionFactory.close();
    }

    @Test
    public void testGetOrderingByUserId() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<OrderingMedicine> orderingMedicines = orderingMedicineDao.getOrderingByUserId(1L);
            assertThat(orderingMedicines, hasSize(2));
            session.getTransaction().commit();
        }
    }

    @Test
    public void testGetOrderingByMedicineId() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<OrderingMedicine> orderingMedicines = orderingMedicineDao.getOrderingByMedicineId(1L);
            assertThat(orderingMedicines, hasSize(2));
            session.getTransaction().commit();
        }
    }
}
