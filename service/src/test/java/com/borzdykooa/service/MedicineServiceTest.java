package com.borzdykooa.service;

import com.borzdykooa.config.TestServiceConfiguration;
import com.borzdykooa.entity.Medicine;
import com.borzdykooa.entity.OrderingMedicine;
import com.borzdykooa.entity.PharmacyGroup;
import com.borzdykooa.entity.Prescription;
import com.borzdykooa.entity.Review;
import com.borzdykooa.entity.SaleInfo;
import com.borzdykooa.util.TestServiceDataImporter;
import org.hamcrest.Matchers;
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
    private TestServiceDataImporter testServiceDataImporter;

    @Before
    public void initDb() {
        testServiceDataImporter.deleteTestData();
        testServiceDataImporter.importTestData();
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
        PharmacyGroup pharmacyGroup = sessionFactory.getCurrentSession().createQuery("select p from PharmacyGroup p ", PharmacyGroup.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(pharmacyGroup);

        List<Medicine> allMedicines = medicineService.findByGroupId(pharmacyGroup.getId());
        assertThat(allMedicines, hasSize(1));
    }

    @Test
    public void testFind() {
        Medicine medicine = sessionFactory.getCurrentSession().createQuery("select m from Medicine m ", Medicine.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(medicine);

        Medicine theSameMedicine = medicineService.find(medicine.getId());
        assertNotNull("Entity is null!", theSameMedicine);
    }

    @Test
    public void testFindAll() {
        List<Medicine> allMedicines = medicineService.findAll();
        List<String> names = allMedicines.stream().map(Medicine::getName).collect(toList());
        assertThat(names, Matchers.hasItem("котоферон"));
    }

    @Test
    public void testSave() {
        PharmacyGroup pharmacyGroup = sessionFactory.getCurrentSession().createQuery("select p from PharmacyGroup p ", PharmacyGroup.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(pharmacyGroup);

        SaleInfo saleInfoNovoferon = new SaleInfo(BigDecimal.valueOf(5.55), 11L, true);

        Medicine novoferon = new Medicine("новоферон", "новое лекарство", pharmacyGroup, saleInfoNovoferon);
        Long id = medicineService.save(novoferon);
        assertNotNull("Entity is not saved", id);
    }

    @Test
    public void testDelete() {
        Review review = sessionFactory.getCurrentSession().createQuery("select r from Review r where r.reviewMedicine.name= 'простоферон'", Review.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(review);
        sessionFactory.getCurrentSession().delete(review);

        OrderingMedicine orderingMedicine = sessionFactory.getCurrentSession().createQuery("select om from OrderingMedicine om where om.medicine.name= 'простоферон'", OrderingMedicine.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(orderingMedicine);
        sessionFactory.getCurrentSession().delete(orderingMedicine);

        Prescription prescription = sessionFactory.getCurrentSession().createQuery("select p from Prescription p where p.prescriptionMedicine.name= 'простоферон'", Prescription.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(prescription);
        sessionFactory.getCurrentSession().delete(prescription);

        Medicine medicine = sessionFactory.getCurrentSession().createQuery("select m from Medicine m where m.name= 'простоферон' ", Medicine.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(medicine);

        medicineService.delete(medicine);

        Medicine theSameMedicine = sessionFactory.getCurrentSession().createQuery("select m from Medicine m where m.name= 'простоферон' ", Medicine.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNull("Entity is not null!", theSameMedicine);
    }

    @Test
    public void testUpdate() {
        Medicine medicine = sessionFactory.getCurrentSession().createQuery("select m from Medicine m ", Medicine.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(medicine);

        medicine.setName("очень новое лекарство");
        medicineService.update(medicine);

        Medicine updatedMedicine = sessionFactory.getCurrentSession().createQuery("select m from Medicine m where m.name='очень новое лекарство'", Medicine.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(updatedMedicine);
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
}
