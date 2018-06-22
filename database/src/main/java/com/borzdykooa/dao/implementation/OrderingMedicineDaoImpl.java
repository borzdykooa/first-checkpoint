package com.borzdykooa.dao.implementation;

import com.borzdykooa.dao.repository.OrderingMedicineDao;
import com.borzdykooa.entity.OrderingMedicine;
import com.borzdykooa.entity.enums.Status;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderingMedicineDaoImpl extends BaseDaoImpl<Long, OrderingMedicine> implements OrderingMedicineDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<OrderingMedicine> findOrderingByUserLogin(String userLogin) {
        return sessionFactory.getCurrentSession().createQuery("select om from OrderingMedicine om join om.ordering o join o.user u where u.login=:user", OrderingMedicine.class)
                .setParameter("user", userLogin)
                .list();
    }

    @Override
    public List<OrderingMedicine> findOrderingByMedicineId(Long medicineId) {
        return sessionFactory.getCurrentSession().createQuery("select om from OrderingMedicine om where om.medicine.id=:medicine", OrderingMedicine.class)
                .setParameter("medicine", medicineId)
                .list();
    }

    @Override
    public List<OrderingMedicine> findProcessedOrdering(Status status) {
        return sessionFactory.getCurrentSession().createQuery("select om from OrderingMedicine om where om.ordering.status=:status", OrderingMedicine.class)
                .setParameter("status", status)
                .list();
    }
}
