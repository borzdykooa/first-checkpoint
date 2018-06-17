package com.borzdykooa.service;

import com.borzdykooa.config.TestServiceConfiguration;
import com.borzdykooa.entity.Client;
import com.borzdykooa.entity.Ordering;
import com.borzdykooa.entity.OrderingMedicine;
import com.borzdykooa.entity.Prescription;
import com.borzdykooa.entity.Review;
import com.borzdykooa.entity.enums.UserRole;
import com.borzdykooa.entity.helpers.FullName;
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
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

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
        Client client = sessionFactory.getCurrentSession().createQuery("select c from Client c ", Client.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(client);

        Client theSameClient = clientService.find(client.getId());
        assertNotNull("Entity is null!", theSameClient);
    }

    @Test
    public void testFindAll() {
        List<Client> groups = clientService.findAll();
        List<String> names = groups.stream().map(Client::getLogin).collect(toList());
        assertThat(names, Matchers.hasItem("ivan"));
    }

    @Test
    public void testSave() {
        Client client = new Client("test", "test", UserRole.CLIENT, new FullName("Test", "test", "Ivanovich"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
        Long id = clientService.save(client);
        assertNotNull("Entity is not saved", id);
    }

    @Test
    public void testDelete() {
        Review review = sessionFactory.getCurrentSession().createQuery("select r from Review r where r.reviewUser.login= 'ivan'", Review.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(review);
        sessionFactory.getCurrentSession().delete(review);

        Prescription prescription = sessionFactory.getCurrentSession().createQuery("select p from Prescription p where p.prescriptionUser.login= 'ivan'", Prescription.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(prescription);
        sessionFactory.getCurrentSession().delete(prescription);

        OrderingMedicine orderingMedicine = sessionFactory.getCurrentSession().createQuery("select om from OrderingMedicine om where om.ordering.user.login='ivan'", OrderingMedicine.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(orderingMedicine);
        sessionFactory.getCurrentSession().delete(orderingMedicine);

        Ordering ordering = sessionFactory.getCurrentSession().createQuery("select o from Ordering o where o.user.login='ivan'", Ordering.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        sessionFactory.getCurrentSession().delete(ordering);

        Client client = sessionFactory.getCurrentSession().createQuery("select c from Client c where c.login='ivan'", Client.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(client);

        clientService.delete(client);

        Client theSameClient = sessionFactory.getCurrentSession().createQuery("select c from Client c where c.login='ivan'", Client.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNull("Entity is not null!", theSameClient);
    }

    @Test
    public void testUpdate() {
        Client client = sessionFactory.getCurrentSession().createQuery("select c from Client c where c.login='ivan'", Client.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(client);

        client.setLogin("new ivan");
        clientService.update(client);

        Client updatedClient = sessionFactory.getCurrentSession().createQuery("select c from Client c where c.login='new ivan'", Client.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(updatedClient);
    }
}
