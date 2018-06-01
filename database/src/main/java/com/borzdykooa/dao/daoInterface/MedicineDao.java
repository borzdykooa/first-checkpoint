package com.borzdykooa.dao.daoInterface;

import com.borzdykooa.entity.Medicine;

import java.util.List;

public interface MedicineDao extends Dao<Long, Medicine> {

    List<Medicine> findByPartName(String partName);

    List<Medicine> findByGroupId(Long groupId);

    List<Medicine> findComplex(int limit, int page, String partName, String partDescription, Boolean needPrescription);
}
