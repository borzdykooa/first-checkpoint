package com.borzdykooa.dao.interfaces;

import com.borzdykooa.entity.OrderingMedicine;

import java.util.List;

public interface OrderingMedicineDaoIF extends DaoIF<Long, OrderingMedicine> {

    List<OrderingMedicine> getOrderingByUserId(Long userId);

    List<OrderingMedicine> getOrderingByMedicineId(Long medicineId);
}
