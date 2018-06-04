package com.borzdykooa.service;

import com.borzdykooa.dao.repository.PrescriptionDao;
import com.borzdykooa.entity.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PrescriptionService {

    private final PrescriptionDao prescriptionDao;

    @Autowired
    public PrescriptionService(PrescriptionDao prescriptionDao) {
        this.prescriptionDao = prescriptionDao;
    }

    public Prescription find(Long id) {
        return prescriptionDao.find(id);
    }

    public List<Prescription> findAll() {
        return prescriptionDao.findAll();
    }

    public Long save(Prescription prescription) {
        return prescriptionDao.save(prescription);
    }

    public void delete(Prescription prescription) {
        prescriptionDao.delete(prescription);
    }

    public void update(Prescription prescription) {
        prescriptionDao.update(prescription);
    }
}
