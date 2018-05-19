package com.borzdykooa.entity;

import com.borzdykooa.entity.enums.Status;
import com.borzdykooa.entity.enums.UserRole;
import com.borzdykooa.entity.helpers.FullName;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderingTest extends BaseEntityTest {

    @Test
    public void checkSaveEntity() {
        Client client = new Client("ivan", "pass", UserRole.CLIENT, new FullName("Ivanov", "Ivan", "Ivanovich"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
        Ordering firstOrder = new Ordering(LocalDate.of(2017, 3, 5), LocalDate.of(2017, 3, 5), Status.DONE, BigDecimal.valueOf(36), client);
        save(client, firstOrder);
    }

    @Test
    public void checkFindEntity() {
        Client client = new Client("ivan2", "pass",UserRole.CLIENT, new FullName("Ivanov2", "Ivan2", "Ivanovich"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
        Ordering secondOrder = new Ordering(LocalDate.of(2017, 3, 5), LocalDate.of(2017, 3, 5), Status.DONE, BigDecimal.valueOf(36), client);
        find(client, secondOrder);
    }
}
