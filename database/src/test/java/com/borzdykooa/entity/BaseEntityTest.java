package com.borzdykooa.entity;

import com.borzdykooa.entity.helpers.IdEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

//@Component
public class BaseEntityTest {

    @Autowired
    SessionFactory sessionFactory;

    @Before
    public void clean() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from OrderingMedicine ").executeUpdate();
            session.createQuery("delete from Prescription ").executeUpdate();
            session.createQuery("delete from Review ").executeUpdate();
            session.createQuery("delete from Ordering ").executeUpdate();
            session.createQuery("delete from Client ").executeUpdate();
            session.createQuery("delete from Medicine ").executeUpdate();
            session.createQuery("delete from Admin ").executeUpdate();
            session.createQuery("delete from User ").executeUpdate();
            session.createQuery("delete from PharmacyGroup ").executeUpdate();
            session.createQuery("delete from SaleInfo ").executeUpdate();
            session.getTransaction().commit();
        }
    }

    public <T extends IdEntity> void save(T... object) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Arrays.asList(object).forEach(it -> {
                session.save(it);
                assertNotNull("Entity is not saved", it.getId());
            });

            session.getTransaction().commit();
        }
    }

    public <T extends IdEntity> void find(T... object) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Arrays.asList(object).forEach(it -> {
                session.save(it);
                session.clear();
                IdEntity idEntity = session.find(it.getClass(), it.getId());
                assertNotNull("Entity is null", idEntity);
            });
            session.getTransaction().commit();
        }
    }
}
