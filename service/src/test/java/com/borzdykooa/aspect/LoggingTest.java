package com.borzdykooa.aspect;

import com.borzdykooa.config.TestServiceConfiguration;
import com.borzdykooa.service.MedicineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestServiceConfiguration.class)
public class LoggingTest {

    @Autowired
    private MedicineService medicineService;

    @Test
    public void test(){
        medicineService.findAll();
    }
}
