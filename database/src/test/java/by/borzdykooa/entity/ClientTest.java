package by.borzdykooa.entity;

import by.borzdykooa.entity.helpers.FullName;
import org.junit.Test;

import java.time.LocalDate;

public class ClientTest extends BaseEntityTest {

    @Test
    public void checkSaveEntity() {
        Client client = new Client("ivan", "pass", new FullName("Ivanov", "Ivan", "Ivanovich"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
        save(client);
    }

    @Test
    public void checkFindEntity() {
        Client client = new Client("ivan2", "pass", new FullName("Ivanov2", "Ivan2", "Ivanovich2"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
        find(client);
    }
}
