package by.borzdykooa.dao;

import by.borzdykooa.entity.Medicine;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MedicineDao {

    private static final MedicineDao INSTANCE = new MedicineDao();

    public Medicine findMedicineById() {
        try (SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Medicine medicine = session.find(Medicine.class, 3L);
            return medicine;
        }
    }

    public static MedicineDao getInstance() {
        return INSTANCE;
    }
}
