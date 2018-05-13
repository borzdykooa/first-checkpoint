package by.borzdykooa.entity;

import by.borzdykooa.entity.enums.Status;
import by.borzdykooa.entity.helpers.FullName;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderingTest extends BaseEntityTest {

    @Test
    public void checkSaveEntity() {

        Client client = new Client("ivan", "pass", new FullName("Ivanov", "Ivan", "Ivanovich"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
        Ordering firstOrder = new Ordering(LocalDate.of(2017, 3, 5), LocalDate.of(2017, 3, 5), Status.DONE, BigDecimal.valueOf(36), client);
        save(client, firstOrder);
    }

    @Test
    public void checkFindEntity() {
        Client client = new Client("ivan2", "pass", new FullName("Ivanov2", "Ivan2", "Ivanovich"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
        Ordering secondOrder = new Ordering(LocalDate.of(2017, 3, 5), LocalDate.of(2017, 3, 5), Status.DONE, BigDecimal.valueOf(36), client);
        find(client, secondOrder);
    }
}
