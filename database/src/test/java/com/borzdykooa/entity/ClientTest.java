package com.borzdykooa.entity;

import com.borzdykooa.config.TestDaoConfiguration;
import com.borzdykooa.entity.enums.UserRole;
import com.borzdykooa.entity.helpers.FullName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDaoConfiguration.class)
@Transactional
public class ClientTest extends BaseEntityTest {

    @Test
    public void checkSaveEntity() {
        Client client = new Client("ivan", "pass", UserRole.CLIENT,new FullName("Ivanov", "Ivan", "Ivanovich"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
        save(client);
    }

    @Test
    public void checkFindEntity() {
        Client client = new Client("ivan2", "pass", UserRole.CLIENT,new FullName("Ivanov2", "Ivan2", "Ivanovich2"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
        find(client);
    }
}
