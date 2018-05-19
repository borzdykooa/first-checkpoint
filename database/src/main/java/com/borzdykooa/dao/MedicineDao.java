package com.borzdykooa.dao;

import com.borzdykooa.entity.Medicine;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MedicineDao {

    private static final MedicineDao INSTANCE = new MedicineDao();

    public Medicine findMedicineById(Long id) {
        try (SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Medicine medicine = session.find(Medicine.class, id);
            session.getTransaction().commit();
            return medicine;
        }
    }



    public static MedicineDao getInstance() {
        return INSTANCE;
    }
}
