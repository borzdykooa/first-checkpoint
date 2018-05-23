package com.borzdykooa.service;

import com.borzdykooa.dao.implementations.PharmacyGroupDao;
import com.borzdykooa.entity.PharmacyGroup;

import java.util.List;

public class PharmacyGroupService {

    private static final PharmacyGroupService INSTANCE = new PharmacyGroupService();

    public List<PharmacyGroup> getAllGroups() {
        return PharmacyGroupDao.getInstance().findAll();
    }

    public static PharmacyGroupService getInstance() {
        return INSTANCE;
    }
}
