package com.borzdykooa.dao.repository;

import com.borzdykooa.entity.OrderingMedicine;
import com.borzdykooa.entity.enums.Status;

import java.util.List;

public interface OrderingMedicineDao extends Dao<Long, OrderingMedicine> {

    List<OrderingMedicine> findOrderingByUserLogin(String userLogin);

    List<OrderingMedicine> findOrderingByMedicineId(Long medicineId);

    List<OrderingMedicine> findProcessedOrdering(Status status);
}
