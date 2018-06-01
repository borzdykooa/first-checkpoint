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
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDaoConfiguration.class)
@Transactional
public class ReviewTest extends BaseEntityTest {

    @Test
    public void checkSaveEntity() {
        Client client = new Client("ivan", "pass", UserRole.CLIENT, new FullName("Ivanov", "Ivan", "Ivanovich"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
        PharmacyGroup antidepressants = new PharmacyGroup("антидепрессанты");
        SaleInfo saleInfoKotoferon=new SaleInfo(BigDecimal.valueOf(5.55), 11L, true);
        Medicine kotoferon = new Medicine("котоферон", "суперлекарство", antidepressants,saleInfoKotoferon);
        Review review = new Review(5, "comment", LocalDateTime.now(), client, kotoferon);
        save(client, antidepressants, saleInfoKotoferon,kotoferon, review);
    }

    @Test
    public void checkFindEntity() {
        Client client = new Client("ivan2", "pass", UserRole.CLIENT,new FullName("Ivanov", "Ivan", "Ivanovich"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
        PharmacyGroup antihistamines = new PharmacyGroup("антигистаминные препараты");
        SaleInfo saleInfoprostoferon=new SaleInfo(BigDecimal.valueOf(5.55), 11L, true);
        Medicine feron = new Medicine("простоферон", "просто лекарство",  antihistamines,saleInfoprostoferon);
        Review review = new Review(9, "comment", LocalDateTime.now(), client, feron);
        find(client, antihistamines, saleInfoprostoferon, feron, review);
    }
}
