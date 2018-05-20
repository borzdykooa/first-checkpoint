package com.borzdykooa.dao.implementations;

import com.borzdykooa.dao.BaseDao;
import com.borzdykooa.dao.interfaces.PharmacyGroupDaoIF;
import com.borzdykooa.entity.PharmacyGroup;

import java.util.List;

public class PharmacyGroupDao extends BaseDao<Long, PharmacyGroup> implements PharmacyGroupDaoIF {

    private static final PharmacyGroupDao INSTANCE = new PharmacyGroupDao();

    @Override
    public Long save(PharmacyGroup pharmacyGroup) {
        return super.save(pharmacyGroup);
    }

    @Override
    public PharmacyGroup find(Long id) {
        return super.find(id);
    }

    @Override
    public List<PharmacyGroup> findAll() {
        return super.findAll();
    }

    @Override
    public void update(PharmacyGroup pharmacyGroup) {
        super.update(pharmacyGroup);
    }

    @Override
    public void delete(PharmacyGroup pharmacyGroup) {
        super.delete(pharmacyGroup);
    }

    public static PharmacyGroupDaoIF getInstance() {
        return INSTANCE;
    }
}
