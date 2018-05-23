package com.borzdykooa.service;

import com.borzdykooa.dao.implementations.MedicineDao;
import com.borzdykooa.entity.Medicine;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MedicineService {

    private static final MedicineService INSTANCE = new MedicineService();

    public Medicine find(Long id) {
        return MedicineDao.getInstance().find(id);
    }


    public Medicine getMedicineById(Long id) {
        return MedicineDao.getInstance().find(id);
    }

    public List<Medicine> getComplex(int limit, int page, String partName, String partDescription, Boolean needPrescription, Long groupId) {
        return MedicineDao.getInstance().findComplex(limit, page, partName, partDescription, needPrescription, groupId);
    }

    public List<Medicine> getAllMedicines() {
        return MedicineDao.getInstance().findAll();
    }

    public static MedicineService getInstance() {
        return INSTANCE;
    }
}
