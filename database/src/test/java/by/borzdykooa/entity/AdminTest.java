package by.borzdykooa.entity;

import by.borzdykooa.entity.enums.Role;
import org.junit.Test;

public class AdminTest extends BaseEntityTest {

    @Test
    public void checkSaveEntity() {

        Admin admin = new Admin("admin", "admin", Role.SUPER_ADMIN);
        save(admin);

    }

    @Test
    public void checkFindEntity() {
        Admin admin = new Admin("manager", "admin", Role.PRESCRIPTION_MANAGER);
        find(admin);
    }
}
