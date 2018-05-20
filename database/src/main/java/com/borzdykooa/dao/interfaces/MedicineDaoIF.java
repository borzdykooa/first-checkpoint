package com.borzdykooa.dao.interfaces;

import com.borzdykooa.entity.Medicine;

import java.util.List;

public interface MedicineDaoIF extends DaoIF<Long, Medicine> {

    List<Medicine> findByPartName(String partName);

    List<Medicine> findByGroupId(Long groupId);

    List<Medicine> findComplex(int limit, int page, String partName, String partDescription, Boolean needPrescription, Long groupId);
}
