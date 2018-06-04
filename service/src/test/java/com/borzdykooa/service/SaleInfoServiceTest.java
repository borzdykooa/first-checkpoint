package com.borzdykooa.service;

import com.borzdykooa.config.TestServiceConfiguration;
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestServiceConfiguration.class)
@Transactional
public class SaleInfoServiceTest {

    @Autowired
    private SaleInfoService saleInfoService;

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
        SaleInfo saleInfo = sessionFactory.getCurrentSession().createQuery("select s from SaleInfo s ", SaleInfo.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(saleInfo);

        SaleInfo theSameSaleInfo = saleInfoService.find(saleInfo.getId());
        assertNotNull("Entity is null!", theSameSaleInfo);
    }

    @Test
    public void testFindAll() {
        List<SaleInfo> groups = saleInfoService.findAll();
        List<BigDecimal> collect = groups.stream().map(SaleInfo::getPrice).collect(toList());
        assertThat(collect, Matchers.hasItem(BigDecimal.valueOf(5.55)));
    }

    @Test
    public void testSave() {
        SaleInfo saleInfoKotoferon = new SaleInfo(BigDecimal.valueOf(2.22), 2L, false);
        Long id = saleInfoService.save(saleInfoKotoferon);
        assertNotNull("Entity is not saved", id);
    }

//    @Test
//    public void testDelete() {
//        SaleInfo saleInfo = sessionFactory.getCurrentSession().createQuery("select s from SaleInfo s where s.quantity=10", SaleInfo.class)
//                .list()
//                .stream()
//                .findFirst()
//                .orElse(null);
//        assertNotNull(saleInfo);
//
//        saleInfoService.delete(saleInfo);
//
//        SaleInfo theSameSaleInfo = sessionFactory.getCurrentSession().createQuery("select s from SaleInfo s where s.quantity=10", SaleInfo.class)
//                .list()
//                .stream()
//                .findFirst()
//                .orElse(null);
//        assertNull("Entity is not null!", theSameSaleInfo);
//    }

    @Test
    public void testUpdate() {
        SaleInfo saleInfo = sessionFactory.getCurrentSession().createQuery("select s from SaleInfo s where s.quantity=11", SaleInfo.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(saleInfo);

        saleInfo.setQuantity(44L);
        saleInfoService.update(saleInfo);

        SaleInfo updatedSaleInfo = sessionFactory.getCurrentSession().createQuery("select s from SaleInfo s where s.quantity=44", SaleInfo.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(updatedSaleInfo);
    }
}
