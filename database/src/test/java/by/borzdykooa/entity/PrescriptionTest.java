package by.borzdykooa.entity;

import by.borzdykooa.entity.helpers.FullName;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PrescriptionTest extends BaseEntityTest {

    @Test
    public void checkSaveEntity() {
        Client client = new Client("ivan3", "pass3", new FullName("Ivanov", "Ivan", "Ivanovich"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
        PharmacyGroup antidepressants = new PharmacyGroup("антидепрессанты1");
        Medicine kotoferon = new Medicine("котоферон5", "суперлекарство", BigDecimal.valueOf(5.55), 11L, true, antidepressants);
        Prescription prescription = new Prescription(7777725838L, client, kotoferon, 15L, LocalDate.of(2018, 5, 31));
        save(client, antidepressants, kotoferon, prescription);
    }

    @Test
    public void checkFindEntity() {
        Client client = new Client("ivan4", "pass4", new FullName("Ivanov", "Ivan", "Ivanovich"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
        PharmacyGroup antihistamines = new PharmacyGroup("антигистаминные препараты1");
        Medicine feron = new Medicine("ферон5", "суперлекарство", BigDecimal.valueOf(5.55), 11L, true, antihistamines);
        Prescription prescription = new Prescription(12583L, client, feron, 15L, LocalDate.of(2018, 5, 31));
        find(client, antihistamines, feron, prescription);
    }
}
