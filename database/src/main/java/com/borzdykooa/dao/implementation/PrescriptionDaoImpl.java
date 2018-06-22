package com.borzdykooa.dao.implementation;

import com.borzdykooa.dao.repository.PrescriptionDao;
import com.borzdykooa.entity.Prescription;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class PrescriptionDaoImpl extends BaseDaoImpl<Long, Prescription> implements PrescriptionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Prescription> findAllByLoginAndMedicineId(String login, Long medicineId, LocalDate date, Long quantity) {
        return sessionFactory.getCurrentSession().createQuery("select p from Prescription p where p.prescriptionUser.login= :login and p.prescriptionMedicine.id=:medicineId and p.quantity>:quantity and p.validity>:date", Prescription.class)
                .setParameter("login", login)
                .setParameter("date", date)
                .setParameter("medicineId", medicineId)
                .setParameter("quantity", quantity)
                .list();
    }

    @Override
    public List<Prescription> findAllByLogin(String login) {
        return sessionFactory.getCurrentSession().createQuery("select p from Prescription p where p.prescriptionUser.login= :login", Prescription.class)
                .setParameter("login", login)
                .list();
    }
}
