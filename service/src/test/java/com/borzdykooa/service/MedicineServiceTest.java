package com.borzdykooa.service;

import com.borzdykooa.config.TestServiceConfiguration;
import com.borzdykooa.entity.Medicine;
import com.borzdykooa.entity.PharmacyGroup;
import com.borzdykooa.entity.SaleInfo;
import org.hamcrest.Matchers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestServiceConfiguration.class)
@Transactional
public class MedicineServiceTest {

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    TestServiceDataImporter testServiceDataImporter;

    @Before
    public void initDb() {
        testServiceDataImporter.deleteTestData();
        testServiceDataImporter.importTestData();
    }

    @Test
    public void testFind() {
        try (Session session = sessionFactory.openSession()) {
            Medicine medicine = session.createQuery("select m from Medicine m ", Medicine.class)
                    .list()
                    .stream()
                    .findFirst()
                    .orElse(null);
            assertNotNull(medicine);

            Medicine theSameMedicine = medicineService.find(medicine.getId());
            assertNotNull("Entity is null!", theSameMedicine);
        }
    }

    @Test
    public void testFindAll() {
        List<Medicine> allMedicines = medicineService.findAll();
        List<String> names = allMedicines.stream().map(Medicine::getName).collect(toList());
        assertThat(names, Matchers.hasItem("котоферон"));
    }

    @Test
    public void testDelete() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Medicine medicine = session.createQuery("select m from Medicine m where m.name= 'простоферон' ", Medicine.class)
                    .list()
                    .stream()
                    .findFirst()
                    .orElse(null);
            assertNotNull(medicine);

            medicineService.delete(medicine);
            session.getTransaction().commit();
            session.clear();

//            session.beginTransaction();
            Medicine theSameMedicine = session.createQuery("select m from Medicine m where m.name= 'простоферон' ", Medicine.class)
                    .list()
                    .stream()
                    .findFirst()
                    .orElse(null);
//            session.getTransaction().commit();
            assertNull("Entity is not null!", theSameMedicine);
        }
    }

    @Test
    public void testSave() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            PharmacyGroup pharmacyGroup = session.createQuery("select p from PharmacyGroup p ", PharmacyGroup.class)
                    .list()
                    .stream()
                    .findFirst()
                    .orElse(null);
            assertNotNull(pharmacyGroup);

            SaleInfo saleInfoNovoferon = new SaleInfo(BigDecimal.valueOf(5.55), 11L, true);
            session.save(saleInfoNovoferon);
            session.getTransaction().commit();

            session.beginTransaction();
            Medicine novoferon = new Medicine("новоферон", "новое лекарство", pharmacyGroup, saleInfoNovoferon);
            Long id = medicineService.save(novoferon);
            session.getTransaction().commit();
            assertNotNull("Entity is not saved", id);
        }
    }


    @Test
    public void testUpdate() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Medicine medicine = session.createQuery("select m from Medicine m ", Medicine.class)
                    .list()
                    .stream()
                    .findFirst()
                    .orElse(null);
            assertNotNull(medicine);

            medicine.setName("очень новое лекарство");
            medicineService.update(medicine);
            session.getTransaction().commit();
//            session.clear();

            Medicine updatedMedicine = session.createQuery("select m from Medicine m where m.name='очень новое лекарство'", Medicine.class)
                    .list()
                    .stream()
                    .findFirst()
                    .orElse(null);
            assertNotNull(updatedMedicine);
//            assertTrue("Update is not performed", medicine.getName().equals("очень новое лекраство"));
        }
    }

    @Test
    public void testFindComplex() {
        List<Medicine> allMedicines = medicineService.findComplex(1, 1, "кот", "супер", true);
        assertThat(allMedicines, hasSize(1));
        List<String> names = allMedicines.stream().map(Medicine::getName).collect(toList());
        assertThat(names, contains("котоферон"));
        List<String> descriptions = allMedicines.stream().map(Medicine::getDescription).collect(toList());
        assertThat(descriptions, contains("суперлекарство"));
    }

    @Test
    public void testFindByPartName() {
        List<Medicine> allMedicines = medicineService.findByPartName("кот");
        assertThat(allMedicines, hasSize(1));
        List<String> names = allMedicines.stream().map(Medicine::getName).collect(toList());
        assertThat(names, contains("котоферон"));
    }

    @Test
    public void testFindByGroupId() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            PharmacyGroup pharmacyGroup = session.createQuery("select p from PharmacyGroup p ", PharmacyGroup.class)
                    .list()
                    .stream()
                    .findFirst()
                    .orElse(null);
            assertNotNull(pharmacyGroup);

            List<Medicine> allMedicines = medicineService.findByGroupId(pharmacyGroup.getId());
            assertThat(allMedicines, hasSize(1));
        }
    }
}
