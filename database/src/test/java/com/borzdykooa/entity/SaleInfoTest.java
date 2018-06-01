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
public class SaleInfoTest extends BaseEntityTest {

    @Test
    public void checkSaveEntity() {
        SaleInfo saleInfoKotoferon = new SaleInfo(BigDecimal.valueOf(5.55), 11L, true);
        save(saleInfoKotoferon);
    }

    @Test
    public void checkFindEntity() {
        SaleInfo saleInfoprostoferon = new SaleInfo(BigDecimal.valueOf(5.55), 11L, true);
        find(saleInfoprostoferon);
    }
}
