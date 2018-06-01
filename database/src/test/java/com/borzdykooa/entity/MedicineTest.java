package com.borzdykooa.entity;

import com.borzdykooa.config.TestDaoConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDaoConfiguration.class)
@Transactional
public class MedicineTest extends BaseEntityTest {

    @Test
    public void checkSaveEntity() {
        PharmacyGroup antidepressants = new PharmacyGroup("антидепрессанты");
        SaleInfo saleInfoKotoferon=new SaleInfo(BigDecimal.valueOf(5.55), 11L, true);
        Medicine kotoferon = new Medicine("котоферон", "суперлекарство", antidepressants,saleInfoKotoferon);
        save(saleInfoKotoferon,antidepressants, kotoferon);
    }

    @Test
    public void checkFindEntity() {
        PharmacyGroup antihistamines = new PharmacyGroup("антигистаминные препараты");
        SaleInfo saleInfoprostoferon=new SaleInfo(BigDecimal.valueOf(5.55), 11L, true);
        Medicine prostoferon = new Medicine("простоферон", "просто лекарство",  antihistamines,saleInfoprostoferon);
        find(saleInfoprostoferon,antihistamines, prostoferon);
    }
}