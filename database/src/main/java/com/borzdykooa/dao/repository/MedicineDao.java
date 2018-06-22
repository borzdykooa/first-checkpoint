package com.borzdykooa.dao.repository;

import com.borzdykooa.dto.PaginationDto;
import com.borzdykooa.entity.Medicine;

import java.util.List;

public interface MedicineDao extends Dao<Long, Medicine> {

    List<Medicine> findByPartName(String partName);

    List<Medicine> findByGroupId(Long groupId);

    List<Medicine> findComplex(PaginationDto paginationDto);

    List<Medicine> findAllNeedPrescription();
}
