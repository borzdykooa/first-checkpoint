package com.borzdykooa.service;

import com.borzdykooa.dao.repository.PrescriptionDao;
import com.borzdykooa.entity.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    public List<Prescription> findAllByLoginAndMedicineId(String login, Long medicineId, LocalDate date, Long quantity) {
        return prescriptionDao.findAllByLoginAndMedicineId(login, medicineId, date, quantity);
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

    public List<Prescription> findAllByLogin(String login) {
        return prescriptionDao.findAllByLogin(login);
    }
}
