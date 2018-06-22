package com.borzdykooa.service;

import com.borzdykooa.config.TestServiceConfiguration;
import com.borzdykooa.entity.PharmacyGroup;
import com.borzdykooa.util.TestServiceDataImporter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestServiceConfiguration.class)
public class CacheTest {

    @Autowired
    private PharmacyGroupService pharmacyGroupService;

    @Autowired
    private TestServiceDataImporter testServiceDataImporter;

    @Before
    public void initDb() {
        testServiceDataImporter.deleteTestData();
        testServiceDataImporter.importTestData();
    }

    @Test
    public void testFindByName() {
        List<PharmacyGroup> groups = pharmacyGroupService.findByName("антидепрессанты");
        List<PharmacyGroup> theSameGroups = pharmacyGroupService.findByName("антидепрессанты");
        assertThat("Results are not equal!", groups.equals(theSameGroups));
    }
}
