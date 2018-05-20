package com.borzdykooa.entity;

import org.junit.Test;

import java.math.BigDecimal;

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
