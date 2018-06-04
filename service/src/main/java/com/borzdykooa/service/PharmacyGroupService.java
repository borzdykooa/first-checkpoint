package com.borzdykooa.service;

import com.borzdykooa.dao.repository.PharmacyGroupDao;
import com.borzdykooa.entity.PharmacyGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PharmacyGroupService {

    private final PharmacyGroupDao pharmacyGroupDao;

    @Autowired
    public PharmacyGroupService(PharmacyGroupDao pharmacyGroupDao) {
        this.pharmacyGroupDao = pharmacyGroupDao;
    }

    public PharmacyGroup find(Long id) {
        return pharmacyGroupDao.find(id);
    }

    public List<PharmacyGroup> findAll() {
        return pharmacyGroupDao.findAll();
    }

    public Long save(PharmacyGroup pharmacyGroup) {
        return pharmacyGroupDao.save(pharmacyGroup);
    }

    public void delete(PharmacyGroup pharmacyGroup) {
        pharmacyGroupDao.delete(pharmacyGroup);
    }

    public void update(PharmacyGroup pharmacyGroup) {
        pharmacyGroupDao.update(pharmacyGroup);
    }
}
