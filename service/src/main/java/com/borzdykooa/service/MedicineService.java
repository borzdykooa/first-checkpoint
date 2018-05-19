package com.borzdykooa.service;

import com.borzdykooa.dao.MedicineDao;
import com.borzdykooa.entity.Medicine;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MedicineService {

    private static final MedicineService INSTANCE = new MedicineService();

    public Medicine getMedicineById(Long id) {
        return MedicineDao.getInstance().findMedicineById(id);
    }

    public static MedicineService getInstance() {
        return INSTANCE;
    }
}
