package com.borzdykooa.dao.daoInterface;

import com.borzdykooa.entity.OrderingMedicine;

import java.util.List;

public interface OrderingMedicineDao extends Dao<Long, OrderingMedicine> {

    List<OrderingMedicine> getOrderingByUserId(Long userId);

    List<OrderingMedicine> getOrderingByMedicineId(Long medicineId);
}
