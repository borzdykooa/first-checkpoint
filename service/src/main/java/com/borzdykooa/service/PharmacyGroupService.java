package com.borzdykooa.service;

import com.borzdykooa.dao.daoInterface.PharmacyGroupDao;
import com.borzdykooa.entity.PharmacyGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PharmacyGroupService {

//    @Autowired
    private final PharmacyGroupDao pharmacyGroupDao;

    @Autowired
    public PharmacyGroupService(PharmacyGroupDao pharmacyGroupDao) {
        this.pharmacyGroupDao = pharmacyGroupDao;
    }

    public List<PharmacyGroup> findAll() {
        return pharmacyGroupDao.findAll();
    }

    public void delete(PharmacyGroup pharmacyGroup){
        pharmacyGroupDao.delete(pharmacyGroup);
    }
}
