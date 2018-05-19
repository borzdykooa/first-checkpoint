package com.borzdykooa.entity;

import org.junit.Test;

import java.math.BigDecimal;

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