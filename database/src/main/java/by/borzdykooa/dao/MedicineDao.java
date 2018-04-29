package by.borzdykooa.dao;

import by.borzdykooa.entity.Medicine;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MedicineDao {

    private static final MedicineDao INSTANCE = new MedicineDao();

    public List<Medicine> getAllMedicines() {
        List<Medicine> medicines = new ArrayList<Medicine>();
        medicines.add(new Medicine("аспирин"));
        medicines.add(new Medicine("цитрамон"));
        medicines.add(new Medicine("корвалол"));
        medicines.add(new Medicine("активированный уголь"));
        return medicines;
    }

    public static MedicineDao getInstance() {
        return INSTANCE;
    }
}
