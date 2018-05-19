package com.borzdykooa.entity;

import org.junit.Test;

public class PharmacyGroupTest extends BaseEntityTest {

    @Test
    public void checkSaveEntity() {
        PharmacyGroup antidepressants = new PharmacyGroup("антидепрессанты");
        save(antidepressants);
    }

    @Test
    public void checkFindEntity() {
        PharmacyGroup antihistamines = new PharmacyGroup("антигистаминные препараты");
        find(antihistamines);
    }
}
