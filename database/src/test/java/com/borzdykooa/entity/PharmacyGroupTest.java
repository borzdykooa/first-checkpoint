package com.borzdykooa.entity;

import com.borzdykooa.config.TestDaoConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDaoConfiguration.class)
@Transactional
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
