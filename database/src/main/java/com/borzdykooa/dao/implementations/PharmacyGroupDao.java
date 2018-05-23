package com.borzdykooa.dao.implementations;

import com.borzdykooa.dao.BaseDao;
import com.borzdykooa.dao.interfaces.PharmacyGroupDaoIF;
import com.borzdykooa.entity.PharmacyGroup;

public class PharmacyGroupDao extends BaseDao<Long, PharmacyGroup> implements PharmacyGroupDaoIF {

    private static final PharmacyGroupDao INSTANCE = new PharmacyGroupDao();

    public static PharmacyGroupDaoIF getInstance() {
        return INSTANCE;
    }
}
