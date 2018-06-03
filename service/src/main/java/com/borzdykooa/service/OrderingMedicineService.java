package com.borzdykooa.service;

import com.borzdykooa.dao.daoInterface.OrderingMedicineDao;
import com.borzdykooa.entity.OrderingMedicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderingMedicineService {

    private final OrderingMedicineDao orderingMedicineDao;

    @Autowired
    public OrderingMedicineService(OrderingMedicineDao orderingMedicineDao) {
        this.orderingMedicineDao = orderingMedicineDao;
    }

    public List<OrderingMedicine> findOrderingByMedicineId(Long id) {
        return orderingMedicineDao.findOrderingByMedicineId(id);
    }

    public List<OrderingMedicine> findOrderingByUserId(Long id) {
        return orderingMedicineDao.findOrderingByUserId(id);
    }

    public OrderingMedicine find(Long id) {
        return orderingMedicineDao.find(id);
    }

    public List<OrderingMedicine> findAll() {
        return orderingMedicineDao.findAll();
    }

    public Long save(OrderingMedicine orderingMedicine) {
        return orderingMedicineDao.save(orderingMedicine);
    }

    public void delete(OrderingMedicine orderingMedicine) {
        orderingMedicineDao.delete(orderingMedicine);
    }

    public void update(OrderingMedicine orderingMedicine) {
        orderingMedicineDao.update(orderingMedicine);
    }
}
