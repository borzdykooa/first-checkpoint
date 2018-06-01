package com.borzdykooa.entity;

import com.borzdykooa.config.TestDaoConfiguration;
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
public class PrescriptionTest extends BaseEntityTest {

    @Test
    public void checkSaveEntity() {
        Client client = new Client("ivan3", "pass3", UserRole.CLIENT,new FullName("Ivanov", "Ivan", "Ivanovich"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
        PharmacyGroup antidepressants = new PharmacyGroup("антидепрессанты1");
        SaleInfo saleInfoKotoferon=new SaleInfo(BigDecimal.valueOf(5.55), 11L, true);
        Medicine kotoferon = new Medicine("котоферон", "суперлекарство", antidepressants,saleInfoKotoferon);
        Prescription prescription = new Prescription(7777725838L, client, kotoferon, 15L, LocalDate.of(2018, 5, 31));
        save(client, antidepressants, saleInfoKotoferon,kotoferon, prescription);
    }

    @Test
    public void checkFindEntity() {
        Client client = new Client("ivan4", "pass4", UserRole.CLIENT,new FullName("Ivanov", "Ivan", "Ivanovich"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
        PharmacyGroup antihistamines = new PharmacyGroup("антигистаминные препараты1");
        SaleInfo saleInfoprostoferon=new SaleInfo(BigDecimal.valueOf(5.55), 11L, true);
        Medicine feron = new Medicine("простоферон", "просто лекарство",  antihistamines,saleInfoprostoferon);
        Prescription prescription = new Prescription(12583L, client, feron, 15L, LocalDate.of(2018, 5, 31));
        find(client, antihistamines, saleInfoprostoferon,feron, prescription);
    }
}
