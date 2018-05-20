package com.borzdykooa.dao;

import com.borzdykooa.dao.implementations.MedicineDao;
import com.borzdykooa.dao.interfaces.MedicineDaoIF;
import com.borzdykooa.entity.Medicine;
import com.borzdykooa.entity.PharmacyGroup;
import com.borzdykooa.entity.SaleInfo;
import org.hamcrest.Matchers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MedicineDaoTest {

    private MedicineDaoIF medicineDao = MedicineDao.getInstance();
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
    public void testFindAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Medicine> allMedicines = medicineDao.findAll();
            List<String> names = allMedicines.stream().map(Medicine::getName).collect(toList());
            assertThat(names, Matchers.hasItem("котоферон"));
        }
    }

    @Test
    public void testFind() {
        Medicine medicine = medicineDao.find(1L);
        assertNotNull("Entity is null!", medicine);
    }

    @Test
    public void testSave() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            PharmacyGroup newGroup = session.find(PharmacyGroup.class, 3L);
            SaleInfo saleInfoNovoferon = session.find(SaleInfo.class, 1L);
            Medicine novoferon = new Medicine("новоферон", "новое лекарство", newGroup, saleInfoNovoferon);
            medicineDao.save(novoferon);
            session.getTransaction().commit();
            session.evict(novoferon);
            assertNotNull("Entity is not saved", novoferon.getId());
        }
    }

    @Test
    public void testDelete() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Medicine medicine = medicineDao.find(2L);
            medicineDao.delete(medicine);
            session.getTransaction().commit();
            session.clear();
            Medicine theSameMedicine = session.find(Medicine.class, 2L);
            assertNull("Entity is not null!", theSameMedicine);
        }
    }

    @Test
    public void testUpdate() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Medicine medicine = medicineDao.find(2L);
            medicine.setName("очень новое лекраство");
            medicineDao.update(medicine);
            session.getTransaction().commit();
            session.clear();
            Medicine newMedicine = session.find(Medicine.class, 2L);
            assertTrue("Update is not performed", newMedicine.getName().equals("очень новое лекраство"));
        }
    }

    @Test
    public void testFindComplex() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Medicine> allMedicines = medicineDao.findComplex(1, 1, "кот", "супер", true, 1L);
            assertThat(allMedicines, hasSize(1));
            List<String> names = allMedicines.stream().map(Medicine::getName).collect(toList());
            assertThat(names, contains("котоферон"));
            List<String> descriptions = allMedicines.stream().map(Medicine::getDescription).collect(toList());
            assertThat(descriptions, contains("суперлекарство"));
            session.getTransaction().commit();
        }
    }

    @Test
    public void testFindByPartName() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Medicine> allMedicines = medicineDao.findByPartName("кот");
            assertThat(allMedicines, hasSize(1));
            List<String> names = allMedicines.stream().map(Medicine::getName).collect(toList());
            assertThat(names, contains("котоферон"));
            session.getTransaction().commit();
        }
    }

    @Test
    public void testFindGroupId() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Medicine> allMedicines = medicineDao.findByGroupId(1L);
            assertThat(allMedicines, hasSize(1));
            session.getTransaction().commit();
        }
    }
}
