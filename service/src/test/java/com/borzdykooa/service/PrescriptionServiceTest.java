package com.borzdykooa.service;

import com.borzdykooa.config.TestServiceConfiguration;
import com.borzdykooa.entity.Client;
import com.borzdykooa.entity.Medicine;
import com.borzdykooa.entity.Prescription;
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

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestServiceConfiguration.class)
@Transactional
public class PrescriptionServiceTest {

    @Autowired
    private PrescriptionService prescriptionService;

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
        Prescription prescription = sessionFactory.getCurrentSession().createQuery("select p from Prescription p ", Prescription.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(prescription);

        Prescription theSamePrescription = prescriptionService.find(prescription.getId());
        assertNotNull("Entity is null!", theSamePrescription);
    }

    @Test
    public void testFindAll() {
        List<Prescription> all = prescriptionService.findAll();
        List<Long> collect = all.stream().map(Prescription::getQuantity).collect(toList());
        assertThat(collect, Matchers.hasItem(1L));
    }

    @Test
    public void testDelete() {
        Prescription prescription = sessionFactory.getCurrentSession().createQuery("select p from Prescription p where p.name=7777725838", Prescription.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(prescription);

        prescriptionService.delete(prescription);

        Prescription deletedPrescription = sessionFactory.getCurrentSession().createQuery("select p from Prescription p where p.name=7777725838", Prescription.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNull("Entity is not null!", deletedPrescription);
    }

    @Test
    public void testSave() {
        Client client = sessionFactory.getCurrentSession().createQuery("select c from Client c ", Client.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(client);

        Medicine medicine = sessionFactory.getCurrentSession().createQuery("select m from Medicine m ", Medicine.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(medicine);

        Prescription prescription = new Prescription(123456L, client, medicine, 14L, LocalDate.of(2018, 5, 31));
        Long id = prescriptionService.save(prescription);
        assertNotNull("Entity is not saved", id);
    }


    @Test
    public void testUpdate() {
        Prescription prescription = sessionFactory.getCurrentSession().createQuery("select p from Prescription p where p.name=12583", Prescription.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(prescription);

        prescription.setName(11111L);
        prescriptionService.update(prescription);

        Prescription updatedPrescription = sessionFactory.getCurrentSession().createQuery("select p from Prescription p where p.name=11111", Prescription.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(updatedPrescription);
    }
}
