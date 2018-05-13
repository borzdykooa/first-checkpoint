package by.borzdykooa.entity;

import by.borzdykooa.entity.helpers.FullName;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReviewTest extends BaseEntityTest {

    @Test
    public void checkSaveEntity() {
        Client client = new Client("ivan", "pass", new FullName("Ivanov", "Ivan", "Ivanovich"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
        PharmacyGroup antidepressants = new PharmacyGroup("антидепрессанты");
        Medicine kotoferon = new Medicine("котоферон", "суперлекарство", BigDecimal.valueOf(5.55), 11L, true, antidepressants);
        Review review = new Review(5, "comment", LocalDateTime.now(), client, kotoferon);
        save(client, antidepressants, kotoferon, review);
    }

    @Test
    public void checkFindEntity() {
        Client client = new Client("ivan2", "pass", new FullName("Ivanov", "Ivan", "Ivanovich"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
        PharmacyGroup antihistamines = new PharmacyGroup("антигистаминные препараты");
        Medicine feron = new Medicine("ферон", "суперлекарство", BigDecimal.valueOf(5.55), 11L, true, antihistamines);
        Review review = new Review(9, "comment", LocalDateTime.now(), client, feron);
        find(client, antihistamines, feron, review);
    }
}
