package com.borzdykooa.entity;

import com.borzdykooa.entity.enums.AdminRole;
import com.borzdykooa.entity.enums.UserRole;
import org.junit.Test;

public class AdminTest extends BaseEntityTest {

    @Test
    public void checkSaveEntity() {
        Admin admin = new Admin("admin", "admin", UserRole.ADMIN, AdminRole.SUPER_ADMIN);
        save(admin);

    }

    @Test
    public void checkFindEntity() {
        Admin admin = new Admin("manager", "admin", UserRole.ADMIN, AdminRole.PRESCRIPTION_MANAGER);
        find(admin);
    }
}
