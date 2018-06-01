package com.borzdykooa.service;

import com.borzdykooa.config.TestServiceConfiguration;
import com.borzdykooa.entity.PharmacyGroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestServiceConfiguration.class)
@Transactional
public class PharmacyGroupServiceTest {

    @Autowired
    private PharmacyGroupService pharmacyGroupService;

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
    public void testDelete() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            PharmacyGroup pharmacyGroup = session.createQuery("select p from PharmacyGroup p where p.name= 'антидепрессанты' ", PharmacyGroup.class)
                    .list()
                    .stream()
                    .findFirst()
                    .orElse(null);
            assertNotNull(pharmacyGroup);

            pharmacyGroupService.delete(pharmacyGroup);
            session.getTransaction().commit();
            session.clear();

            PharmacyGroup theSamePharmacyGroup = session.createQuery("select p from PharmacyGroup p where p.name= 'антидепрессанты' ", PharmacyGroup.class)
                    .list()
                    .stream()
                    .findFirst()
                    .orElse(null);
            assertNull("Entity is not null!", theSamePharmacyGroup);
        }
    }
}
