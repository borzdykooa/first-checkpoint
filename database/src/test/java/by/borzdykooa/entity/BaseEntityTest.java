package by.borzdykooa.entity;

import by.borzdykooa.entity.helpers.IdEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

public class BaseEntityTest {

    protected static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @BeforeClass
    public static void before() {
        FACTORY.openSession();
    }

    public <T extends IdEntity> void save(T... object) {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            clean(session);

            Arrays.asList(object).forEach(it -> {
                session.save(it);
                assertNotNull("Entity is not saved", it.getId());
            });

            session.getTransaction().commit();
            session.close();
        }
    }

    public <T extends IdEntity> void find(T... object) {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            clean(session);

            Arrays.asList(object).forEach(it -> {
                session.save(it);
                IdEntity idEntity = session.find(it.getClass(), it.getId());
                assertNotNull("Entity is null", idEntity);
            });

            session.getTransaction().commit();
            session.close();
        }
    }

    private void clean(Session session) {
        session.createQuery("delete from OrderingMedicine ").executeUpdate();
        session.createQuery("delete from Prescription ").executeUpdate();
        session.createQuery("delete from Review ").executeUpdate();
        session.createQuery("delete from Ordering ").executeUpdate();
        session.createQuery("delete from Client ").executeUpdate();
        session.createQuery("delete from Medicine ").executeUpdate();
        session.createQuery("delete from Admin ").executeUpdate();
        session.createQuery("delete from User ").executeUpdate();
        session.createQuery("delete from PharmacyGroup ").executeUpdate();
    }
}
