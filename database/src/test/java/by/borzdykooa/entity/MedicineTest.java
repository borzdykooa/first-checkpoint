package by.borzdykooa.entity;

import org.junit.Test;

import java.math.BigDecimal;

public class MedicineTest extends BaseEntityTest {

    @Test
    public void checkSaveEntity() {

        PharmacyGroup antidepressants = new PharmacyGroup("антидепрессанты");
        Medicine kotoferon = new Medicine("котоферон", "суперлекарство", BigDecimal.valueOf(5.55), 11L, true, antidepressants);
        save(antidepressants, kotoferon);
    }

    @Test
    public void checkFindEntity() {
        PharmacyGroup antihistamines = new PharmacyGroup("антигистаминные препараты");
        Medicine prostoferon = new Medicine("простоферон", "просто лекарство", BigDecimal.valueOf(1.55), 101L, true, antihistamines);
        find(antihistamines, prostoferon);
    }
}