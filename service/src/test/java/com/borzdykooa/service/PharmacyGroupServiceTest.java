package com.borzdykooa.service;

import com.borzdykooa.config.TestServiceConfiguration;
import com.borzdykooa.entity.Medicine;
import com.borzdykooa.entity.OrderingMedicine;
import com.borzdykooa.entity.PharmacyGroup;
import com.borzdykooa.entity.Prescription;
import com.borzdykooa.entity.Review;
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

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestServiceConfiguration.class)
@Transactional
public class PharmacyGroupServiceTest {

    @Autowired
    private PharmacyGroupService pharmacyGroupService;

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
    public void testFind() {
        PharmacyGroup pharmacyGroup = sessionFactory.getCurrentSession().createQuery("select p from PharmacyGroup p ", PharmacyGroup.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(pharmacyGroup);

        PharmacyGroup theSamePharmacyGroup = pharmacyGroupService.find(pharmacyGroup.getId());
        assertNotNull("Entity is null!", theSamePharmacyGroup);
    }

    @Test
    public void testFindAll() {
        List<PharmacyGroup> groups = pharmacyGroupService.findAll();
        List<String> names = groups.stream().map(PharmacyGroup::getName).collect(toList());
        assertThat(names, Matchers.hasItem("антигистаминные препараты"));
    }

    @Test
    public void testSave() {
        PharmacyGroup pharmacyGroup = new PharmacyGroup("тестовая группа");
        Long id = pharmacyGroupService.save(pharmacyGroup);
        assertNotNull("Entity is not saved", id);
    }

    @Test
    public void testDelete() {
        Review review = sessionFactory.getCurrentSession().createQuery("select r from Review r where r.reviewMedicine.pharmacyGroup.name='антидепрессанты'", Review.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(review);
        sessionFactory.getCurrentSession().delete(review);

        OrderingMedicine orderingMedicine = sessionFactory.getCurrentSession().createQuery("select om from OrderingMedicine om where om.medicine.pharmacyGroup.name='антидепрессанты'", OrderingMedicine.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(orderingMedicine);
        sessionFactory.getCurrentSession().delete(orderingMedicine);

        Prescription prescription = sessionFactory.getCurrentSession().createQuery("select p from Prescription p where p.prescriptionMedicine.pharmacyGroup.name='антидепрессанты'", Prescription.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(prescription);
        sessionFactory.getCurrentSession().delete(prescription);

        Medicine medicine = sessionFactory.getCurrentSession().createQuery("select m from Medicine m where m.pharmacyGroup.name='антидепрессанты' ", Medicine.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(medicine);
        sessionFactory.getCurrentSession().delete(medicine);

        PharmacyGroup pharmacyGroup = sessionFactory.getCurrentSession().createQuery("select p from PharmacyGroup p where p.name= 'антидепрессанты' ", PharmacyGroup.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(pharmacyGroup);

        pharmacyGroupService.delete(pharmacyGroup);

        PharmacyGroup theSamePharmacyGroup = sessionFactory.getCurrentSession().createQuery("select p from PharmacyGroup p where p.name= 'антидепрессанты' ", PharmacyGroup.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNull("Entity is not null!", theSamePharmacyGroup);
    }

    @Test
    public void testUpdate() {
        PharmacyGroup pharmacyGroup = sessionFactory.getCurrentSession().createQuery("select p from PharmacyGroup p where p.name= 'антидепрессанты' ", PharmacyGroup.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(pharmacyGroup);

        pharmacyGroup.setName("очень новая группа");
        pharmacyGroupService.update(pharmacyGroup);

        PharmacyGroup updatedPharmacyGroup = sessionFactory.getCurrentSession().createQuery("select p from PharmacyGroup p where p.name= 'очень новая группа' ", PharmacyGroup.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(updatedPharmacyGroup);
    }
}
