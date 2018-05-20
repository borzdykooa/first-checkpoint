package com.borzdykooa.dao.implementations;

import com.borzdykooa.dao.BaseDao;
import com.borzdykooa.dao.interfaces.OrderingMedicineDaoIF;
import com.borzdykooa.entity.OrderingMedicine;
import org.hibernate.Session;

import java.util.List;

public class OrderingMedicineDao extends BaseDao<Long, OrderingMedicine> implements OrderingMedicineDaoIF {

    private static final OrderingMedicineDao INSTANCE = new OrderingMedicineDao();

    @Override
    public List<OrderingMedicine> getOrderingByUserId(Long userId) {
        try (Session session = SESSION_FACTORY.openSession()) {
            return session
                    .createQuery("select om from OrderingMedicine om join om.ordering o join o.user u where u.id=:user", OrderingMedicine.class)
                    .setParameter("user", userId)
                    .list();
        }
    }

    @Override
    public List<OrderingMedicine> getOrderingByMedicineId(Long medicineId) {
        try (Session session = SESSION_FACTORY.openSession()) {
            return session
                    .createQuery("select om from OrderingMedicine om where om.medicine.id=:medicine", OrderingMedicine.class)
                    .setParameter("medicine", medicineId)
                    .list();
        }
    }

    @Override
    public Long save(OrderingMedicine orderingMedicine) {
        return super.save(orderingMedicine);
    }

    @Override
    public OrderingMedicine find(Long id) {
        return super.find(id);
    }

    @Override
    public List<OrderingMedicine> findAll() {
        return super.findAll();
    }

    @Override
    public void update(OrderingMedicine orderingMedicine) {
        super.update(orderingMedicine);
    }

    @Override
    public void delete(OrderingMedicine orderingMedicine) {
        super.delete(orderingMedicine);
    }

    public static OrderingMedicineDaoIF getInstance() {
        return INSTANCE;
    }
}
