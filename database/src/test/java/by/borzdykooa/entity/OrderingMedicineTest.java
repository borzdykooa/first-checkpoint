package by.borzdykooa.entity;

import by.borzdykooa.entity.enums.Status;
import by.borzdykooa.entity.helpers.FullName;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderingMedicineTest extends BaseEntityTest {

    @Test
    public void checkSaveEntity() {
        Client client = new Client("ivan", "pass", new FullName("Ivanov", "Ivan", "Ivanovich"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
        Ordering firstOrder = new Ordering(LocalDate.of(2017, 3, 5), LocalDate.of(2017, 3, 5), Status.DONE, BigDecimal.valueOf(36), client);
        PharmacyGroup antidepressants = new PharmacyGroup("антидепрессанты");
        Medicine kotoferon = new Medicine("котоферон", "суперлекарство", BigDecimal.valueOf(5.55), 11L, true, antidepressants);
        OrderingMedicine orderingMedicine = new OrderingMedicine(firstOrder, kotoferon, 15L);
        save(client, firstOrder, antidepressants, kotoferon, orderingMedicine);
    }

    @Test
    public void checkFindEntity() {
        Client client = new Client("ivan2", "pass", new FullName("Ivanov", "Ivan", "Ivanovich"), LocalDate.of(2000, 3, 6), "123456", "Minsk Mira 3/5");
        Ordering firstOrder = new Ordering(LocalDate.of(2018, 3, 5), LocalDate.of(2018, 3, 5), Status.DONE, BigDecimal.valueOf(36), client);
        PharmacyGroup antihistamines = new PharmacyGroup("антигистаминные препараты");
        Medicine feron = new Medicine("ферон", "суперлекарство", BigDecimal.valueOf(5.55), 11L, true, antihistamines);
        OrderingMedicine orderingMedicine = new OrderingMedicine(firstOrder, feron, 15L);
        find(client, firstOrder, antihistamines, feron, orderingMedicine);
    }
}
