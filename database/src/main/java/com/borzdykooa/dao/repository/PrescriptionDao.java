package com.borzdykooa.dao.repository;

import com.borzdykooa.entity.Prescription;

import java.time.LocalDate;
import java.util.List;

public interface PrescriptionDao extends Dao<Long, Prescription> {

    List<Prescription> findAllByLoginAndMedicineId(String login, Long medicineId, LocalDate date, Long quantity);

    List<Prescription> findAllByLogin(String login);
}
