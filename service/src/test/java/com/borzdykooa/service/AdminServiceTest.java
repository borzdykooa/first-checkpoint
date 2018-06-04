package com.borzdykooa.service;

import com.borzdykooa.config.TestServiceConfiguration;
import com.borzdykooa.entity.Admin;
import com.borzdykooa.entity.enums.AdminRole;
import com.borzdykooa.entity.enums.UserRole;
import com.borzdykooa.util.TestServiceDataImporter;
import org.hamcrest.Matchers;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestServiceConfiguration.class)
@Transactional
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private TestServiceDataImporter testServiceDataImporter;

    @Before
    public void initDb() {
        testServiceDataImporter.deleteTestData();
        testServiceDataImporter.importTestData();
    }

    @Test
    public void testFind() {
        Admin admin = sessionFactory.getCurrentSession().createQuery("select a from Admin a ", Admin.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(admin);

        Admin theSameAdmin = adminService.find(admin.getId());
        assertNotNull("Entity is null!", theSameAdmin);
    }

    @Test
    public void testFindAll() {
        List<Admin> groups = adminService.findAll();
        List<String> names = groups.stream().map(Admin::getLogin).collect(toList());
        assertThat(names, Matchers.hasItem("admin"));
    }

    @Test
    public void testSave() {
        Admin admin = new Admin("new manager", "new", UserRole.ADMIN, AdminRole.PRESCRIPTION_MANAGER);
        Long id = adminService.save(admin);
        assertNotNull("Entity is not saved", id);
    }

    @Test
    public void testDelete() {
        Admin admin = sessionFactory.getCurrentSession().createQuery("select a from Admin a where a.login='manager'", Admin.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(admin);

        adminService.delete(admin);

        Admin theSameAdmin = sessionFactory.getCurrentSession().createQuery("select a from Admin a where a.login='manager'", Admin.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNull("Entity is not null!", theSameAdmin);
    }

    @Test
    public void testUpdate() {
        Admin admin = sessionFactory.getCurrentSession().createQuery("select a from Admin a where a.login='admin'", Admin.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(admin);

        admin.setLogin("simple");
        adminService.update(admin);

        Admin updatedAdmin = sessionFactory.getCurrentSession().createQuery("select a from Admin a where a.login='simple'", Admin.class)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
        assertNotNull(updatedAdmin);
    }
}
