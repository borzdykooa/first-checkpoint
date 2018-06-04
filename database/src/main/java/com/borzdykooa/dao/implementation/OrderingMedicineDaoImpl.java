package com.borzdykooa.dao.implementation;

import com.borzdykooa.dao.repository.OrderingMedicineDao;
import com.borzdykooa.entity.OrderingMedicine;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderingMedicineDaoImpl extends BaseDaoImpl<Long, OrderingMedicine> implements OrderingMedicineDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<OrderingMedicine> findOrderingByUserId(Long userId) {
        return sessionFactory.getCurrentSession().createQuery("select om from OrderingMedicine om join om.ordering o join o.user u where u.id=:user", OrderingMedicine.class)
                .setParameter("user", userId)
                .list();
    }

    @Override
    public List<OrderingMedicine> findOrderingByMedicineId(Long medicineId) {
        return sessionFactory.getCurrentSession().createQuery("select om from OrderingMedicine om where om.medicine.id=:medicine", OrderingMedicine.class)
                .setParameter("medicine", medicineId)
                .list();
    }
}
