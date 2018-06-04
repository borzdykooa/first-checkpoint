package com.borzdykooa.service;

import com.borzdykooa.dao.repository.MedicineDao;
import com.borzdykooa.entity.Medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MedicineService {

    private final MedicineDao medicineDao;

    @Autowired
    public MedicineService(MedicineDao medicineDao) {
        this.medicineDao = medicineDao;
    }

    public List<Medicine> findComplex(int limit, int page, String partName, String partDescription, Boolean needPrescription) {
        return medicineDao.findComplex(limit, page, partName, partDescription, needPrescription);
    }

    public List<Medicine> findByPartName(String partName) {
        return medicineDao.findByPartName(partName);
    }

    public List<Medicine> findByGroupId(Long groupId) {
        return medicineDao.findByGroupId(groupId);
    }

    public Medicine find(Long id) {
        return medicineDao.find(id);
    }

    public List<Medicine> findAll() {
        return medicineDao.findAll();
    }

    public Long save(Medicine medicine) {
        return medicineDao.save(medicine);
    }

    public void delete(Medicine medicine) {
        medicineDao.delete(medicine);
    }

    public void update(Medicine medicine) {
        medicineDao.update(medicine);
    }
}
