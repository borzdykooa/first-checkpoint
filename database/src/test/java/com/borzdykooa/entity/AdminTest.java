package com.borzdykooa.entity;

import com.borzdykooa.config.TestDaoConfiguration;
import com.borzdykooa.entity.enums.AdminRole;
import com.borzdykooa.entity.enums.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDaoConfiguration.class)
@Transactional
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
