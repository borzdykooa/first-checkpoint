package com.borzdykooa.entity;

import com.borzdykooa.config.TestDaoConfiguration;
import com.borzdykooa.entity.enums.Status;
import com.borzdykooa.entity.enums.UserRole;
import com.borzdykooa.entity.helpers.FullName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDaoConfiguration.class)
@Transactional
public class OrderingMedicineTest extends BaseEntityTest {

    @Test
    public void checkSaveEntity() {
        Client client = new Client("ivan", "pass", UserRole.CLIENT,new FullName("Ivanov", "Ivan", "Ivanovich"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
        Ordering firstOrder = new Ordering(LocalDate.of(2017, 3, 5), LocalDate.of(2017, 3, 5), Status.DONE, BigDecimal.valueOf(36), client);
        PharmacyGroup antidepressants = new PharmacyGroup("антидепрессанты");
        SaleInfo saleInfoKotoferon=new SaleInfo(BigDecimal.valueOf(5.55), 11L, true);
        Medicine kotoferon = new Medicine("котоферон", "суперлекарство", antidepressants,saleInfoKotoferon);
        OrderingMedicine orderingMedicine = new OrderingMedicine(firstOrder, kotoferon, 15L);
        save(client, firstOrder, antidepressants,saleInfoKotoferon, kotoferon, orderingMedicine);
    }

    @Test
    public void checkFindEntity() {
        Client client = new Client("ivan2", "pass", UserRole.CLIENT,new FullName("Ivanov", "Ivan", "Ivanovich"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
        Ordering firstOrder = new Ordering(LocalDate.of(2018, 3, 5), LocalDate.of(2018, 3, 5), Status.DONE, BigDecimal.valueOf(36), client);
        PharmacyGroup antihistamines = new PharmacyGroup("антигистаминные препараты");
        SaleInfo saleInfoEron=new SaleInfo(BigDecimal.valueOf(5.55), 12L, true);
        Medicine eron = new Medicine("ерон", "просто лекарство",  antihistamines,saleInfoEron);
        OrderingMedicine orderingMedicine = new OrderingMedicine(firstOrder, eron, 15L);
        find(client, firstOrder, antihistamines, saleInfoEron,eron, orderingMedicine);
    }
}
