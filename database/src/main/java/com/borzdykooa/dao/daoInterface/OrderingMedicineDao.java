package com.borzdykooa.dao.daoInterface;

import com.borzdykooa.entity.OrderingMedicine;

import java.util.List;

public interface OrderingMedicineDao extends Dao<Long, OrderingMedicine> {

    List<OrderingMedicine> findOrderingByUserId(Long userId);

    List<OrderingMedicine> findOrderingByMedicineId(Long medicineId);
}
