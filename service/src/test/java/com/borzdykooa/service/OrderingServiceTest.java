package com.borzdykooa.service;

import com.borzdykooa.config.TestServiceConfiguration;
import com.borzdykooa.entity.Client;
import com.borzdykooa.entity.Ordering;
import com.borzdykooa.entity.enums.Status;
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
import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestServiceConfiguration.class)
@Transactional
public class OrderingServiceTest {

    @Autowired
    private OrderingService orderingService;

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
        Ordering ordering = sessionFactory.getCurrentSession().createQuery("select o from Ordering o ", Ordering.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(ordering);

        Ordering theSameOrdering = orderingService.find(ordering.getId());
        assertNotNull("Entity is null!", theSameOrdering);
    }

    @Test
    public void testFindAll() {
        List<Ordering> orderings = orderingService.findAll();
        List<Status> collect = orderings.stream().map(Ordering::getStatus).collect(toList());
        assertThat(collect, Matchers.hasItem(Status.DONE));
    }

    @Test
    public void testSave() {
        Client client = sessionFactory.getCurrentSession().createQuery("select c from Client c ", Client.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(client);
        Ordering testOrder = new Ordering(LocalDate.of(2017, 3, 5), LocalDate.of(2017, 3, 5), Status.DONE, BigDecimal.valueOf(55), client);
        Long id = orderingService.save(testOrder);
        assertNotNull("Entity is not saved", id);
    }

//    @Test
//    public void testDelete() {
//        Ordering ordering = sessionFactory.getCurrentSession().createQuery("select o from Ordering o where o.totalSum=36", Ordering.class)
//                .list()
//                .stream()
//                .findFirst()
//                .orElse(null);
//        assertNotNull(ordering);
//
//        orderingService.delete(ordering);
//
//        Ordering theSameOrdering = sessionFactory.getCurrentSession().createQuery("select o from Ordering o where o.totalSum=36", Ordering.class)
//                .list()
//                .stream()
//                .findFirst()
//                .orElse(null);
//        assertNull("Entity is not null!", theSameOrdering);
//    }

    @Test
    public void testUpdate() {
        Ordering ordering = sessionFactory.getCurrentSession().createQuery("select o from Ordering o where o.dateOfOrdering='2017-03-25'", Ordering.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(ordering);

        ordering.setDateOfOrdering(LocalDate.of(2017, 3, 31));
        orderingService.update(ordering);

        Ordering updatedOrdering = sessionFactory.getCurrentSession().createQuery("select o from Ordering o where o.dateOfOrdering='2017-03-31'", Ordering.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(updatedOrdering);
    }
}
