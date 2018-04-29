package by.borzdykooa.service;

import by.borzdykooa.dao.MedicineDao;
import by.borzdykooa.entity.Medicine;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MedicineService {

    private static final MedicineService INSTANCE = new MedicineService();

    public List<Medicine> findAllMedicines() {
        return MedicineDao.getInstance().getAllMedicines();
    }

    public static MedicineService getInstance() {
        return INSTANCE;
    }
}
