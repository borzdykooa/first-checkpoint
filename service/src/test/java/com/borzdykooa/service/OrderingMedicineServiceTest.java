package com.borzdykooa.service;

import com.borzdykooa.config.TestServiceConfiguration;
import com.borzdykooa.entity.Client;
import com.borzdykooa.entity.Medicine;
import com.borzdykooa.entity.Ordering;
import com.borzdykooa.entity.OrderingMedicine;
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
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestServiceConfiguration.class)
@Transactional
public class OrderingMedicineServiceTest {

    @Autowired
    private OrderingMedicineService orderingMedicineService;

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
    public void findOrderingByMedicineId() {
        Medicine medicine = sessionFactory.getCurrentSession().createQuery("select m from Medicine m where m.name='котоферон'", Medicine.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(medicine);

        List<OrderingMedicine> orderingByMedicineId = orderingMedicineService.findOrderingByMedicineId(medicine.getId());
        assertThat(orderingByMedicineId, hasSize(1));
    }

    @Test
    public void findOrderingByUserId() {
        Client client = sessionFactory.getCurrentSession().createQuery("select c from Client c where c.login='ivan'", Client.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(client);

        List<OrderingMedicine> orderingByMedicineId = orderingMedicineService.findOrderingByUserId(client.getId());
        assertThat(orderingByMedicineId, hasSize(1));
    }

    @Test
    public void testFind() {
        OrderingMedicine orderingMedicine = sessionFactory.getCurrentSession().createQuery("select om from OrderingMedicine om ", OrderingMedicine.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(orderingMedicine);

        OrderingMedicine theSameOederingMedicine = orderingMedicineService.find(orderingMedicine.getId());
        assertNotNull("Entity is null!", theSameOederingMedicine);
    }

    @Test
    public void testFindAll() {
        List<OrderingMedicine> all = orderingMedicineService.findAll();
        List<Long> collect = all.stream().map(OrderingMedicine::getQuantity).collect(toList());
        assertThat(collect, Matchers.hasItem(15L));
    }

    @Test
    public void testSave() {
        Ordering ordering = sessionFactory.getCurrentSession().createQuery("select o from Ordering o ", Ordering.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(ordering);

        Medicine medicine = sessionFactory.getCurrentSession().createQuery("select m from Medicine m ", Medicine.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(medicine);

        OrderingMedicine orderingMedicine = new OrderingMedicine(ordering, medicine, 22L);
        Long id = orderingMedicineService.save(orderingMedicine);
        assertNotNull("Entity is not saved", id);
    }

    @Test
    public void testDelete() {
        OrderingMedicine orderingMedicine = sessionFactory.getCurrentSession().createQuery("select om from OrderingMedicine om where om.quantity=1", OrderingMedicine.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(orderingMedicine);

        orderingMedicineService.delete(orderingMedicine);

        OrderingMedicine deletedOrderingMedicine = sessionFactory.getCurrentSession().createQuery("select om from OrderingMedicine om where om.quantity=1", OrderingMedicine.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNull("Entity is not null!", deletedOrderingMedicine);
    }

    @Test
    public void testUpdate() {
        OrderingMedicine orderingMedicine = sessionFactory.getCurrentSession().createQuery("select om from OrderingMedicine om where om.quantity=15", OrderingMedicine.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(orderingMedicine);

        orderingMedicine.setQuantity(55L);
        orderingMedicineService.update(orderingMedicine);

        OrderingMedicine updatedOrderingMedicine = sessionFactory.getCurrentSession().createQuery("select om from OrderingMedicine om where om.quantity=55", OrderingMedicine.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(updatedOrderingMedicine);
    }
}
